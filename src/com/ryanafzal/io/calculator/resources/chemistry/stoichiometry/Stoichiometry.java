package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.LaTeXEquation;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoadComponent;
import com.ryanafzal.io.calculator.resources.units.InvalidUnitException;
import com.ryanafzal.io.calculator.resources.units.MoleUnit;
import com.ryanafzal.io.calculator.resources.units.QuantityUnit;
import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class Stoichiometry {
	
	private ChemicalEquation equation;
	
	public Stoichiometry(ChemicalEquation equation) {
		this.equation = equation;
	}
	
	/**
	 * Gets the limiting reactant from a set of inputs
	 * @param inputs A {@code ChemicalValue} array containing the values to solve for. 
	 * @return Returns a LaTeXEquation containing the problem.
	 * @throws EquationException
	 */
	public LaTeXEquation getLimitingReactant(ChemicalValue[] inputs) throws EquationException {
		ChemicalValue firstproduct = this.equation.getFirstProduct();
		RailRoad[] mole_values_solved = new RailRoad[inputs.length];
		
		if (inputs.length <= 0) {
			throw new IllegalArgumentException("Cannot except an empty input array.");
		}
		
		for (int i = 0; i < inputs.length; i++) {
			try {
				mole_values_solved[i] = this.solveFor(inputs[i], firstproduct.getChemical(), new MoleUnit());
			} catch (InvalidUnitException e) {
				throw new EquationException("==You should never see this==");
			}
		}
		
		
		
		double min = Arrays.asList(mole_values_solved).stream().map(RailRoad::solve).map(UnitValue::getValue).mapToDouble(d -> d).min().orElse(0);
		ChemicalValue limitingreactant = Arrays.asList(inputs).stream().filter(input -> input.getValue() == min).findFirst().orElse(null);
		
		
		
		return null;
	}
	
	/**
	 * Solves the stoichiometry problem for the given {@code Chemical}, in the given {@code Unit}.
	 * 
	 * Process:
	 * 
	 * Convert {@code Prefix} to {@code Prefix.NONE}. 
	 * 
	 * Convert {@code Unit} to {@code QuantityUnit}.
	 * 
	 * Convert {@code QuantityUnit} to {@code MoleUnit} depending on the molar mass of the {@code Chemical}.
	 * 
	 * Perform the stoichiometric operation.
	 * 
	 * Convert {@code Unit} to desired output {@code Unit}.
	 * 
	 * @param chemical The {@code Chemical} to solve for
	 * @param unit The {@code Unit} to return the value in
	 * @return Returns a {@code RailRoad} containing the entire stoichiometric solution. This does not include other equations, such as Gas Laws.
	 * 
	 */
	public RailRoad solveFor(ChemicalValue startingValue, Chemical chemical, Unit unit) throws EquationException, InvalidUnitException {
		RailRoad railroad = new RailRoad(startingValue);
		ChemicalValue chemicalvalue = this.equation.getChemicalValue(chemical);
		
		Unit starting_unit = startingValue.getUnit();
		
		
		//TODO: Since the only acceptable units are grams and moles, throw an exception if anything else is passed.
		if (!startingValue.getUnit().getClass().equals(MoleUnit.class) && !startingValue.getUnit().getClass().equals(QuantityUnit.class)) {
			throw new InvalidUnitException("Unit " + unit + " is not a valid unit.");
		}
		
		
		/*
		 * Convert Prefix to Prefix.NONE
		 */
		if (!starting_unit.getPrefix().equals(Prefix.NONE)) {
			railroad.addComponent(UnitValue.getConversionToPrefix(starting_unit, Prefix.NONE));
		}
		
		
		/*
		 * Convert Unit to QuantityUnit
		 */
		if (!(starting_unit instanceof MoleUnit)) {
			railroad.addComponent(
					new RailRoadComponent(
							new ChemicalValue(
									1, 
									new MoleUnit(), 
									startingValue.getChemical(),
									startingValue.getState()
									), 
							new ChemicalValue(
									startingValue.getChemical().getMolarMass(), 
									starting_unit, 
									startingValue.getChemical(),
									startingValue.getState())));
		}
		
		
		//Perform stoichiometric operation
		double[] conversion = this.equation.getRatioBetween(chemical, startingValue.getChemical());
		railroad.addComponent(new RailRoadComponent(
				new ChemicalValue(conversion[0], new MoleUnit(), chemical, chemicalvalue.getState()), 
				new ChemicalValue(conversion[1], new MoleUnit(), startingValue.getChemical(), startingValue.getState())));
		
		
		
		//If not in the desired units, convert units from moles to 'units'
		if (!(unit instanceof MoleUnit)) {
			//Convert to 'grams' first
				railroad.addComponent(
						new RailRoadComponent(
								new ChemicalValue(
										chemical.getMolarMass(), 
										new QuantityUnit(), 
										chemical, 
										chemicalvalue.getState()
										),
								new ChemicalValue(
										1, 
										new MoleUnit(), 
										chemical,
										chemicalvalue.getState()
										)));
				
			//Convert to other units
		}
		
		//If unit is not in the desired prefix, convert prefix.
		if (unit.getPrefix() != Prefix.NONE) {
			railroad.addComponent(UnitValue.getConversionToPrefix(unit.clone(Prefix.NONE), unit.getPrefix()));
		}
		
		
		//Return the RailRoad (Dimensional Analysis) representing the stoichiometric solution.
		return railroad;
	}
	
}
