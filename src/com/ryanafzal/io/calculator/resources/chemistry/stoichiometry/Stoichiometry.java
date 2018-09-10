package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import java.lang.reflect.InvocationTargetException;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
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
	private ChemicalValue startingValue;
	
	public Stoichiometry(ChemicalEquation equation, ChemicalValue startingValue) {
		this.equation = equation;
		this.startingValue = startingValue;
	}
	
	public RailRoad solveFor(Chemical chemical, Class<? extends Unit> unit) throws EquationException, InvalidUnitException {
		RailRoad railroad = new RailRoad(this.startingValue);
		ChemicalValue new_chemical = this.equation.getChemicalValue(chemical);
		
		//TODO: Since the only acceptable units are grams and moles, throw an exception if anything else is passed.
		if (!this.startingValue.getUnit().getClass().equals(MoleUnit.class) && !this.startingValue.getUnit().getClass().equals(QuantityUnit.class)) {
			throw new IllegalArgumentException("Unit " + unit + " is not a valid unit.");
		}
		
		//Handle conversion of prefixes PREFIX -> NONE
		if (!this.startingValue.getUnit().getPrefix().equals(Prefix.NONE)) {
			
			try {
				if (this.startingValue.getUnit().getPrefix().getRatio() < Prefix.NONE.getRatio()) {
					railroad.addComponent(
							new RailRoadComponent(
									new UnitValue(
											1, 
											this.startingValue.getUnit().getClass().newInstance()
											), 
									new UnitValue(
											(1 / this.startingValue.getUnit().getPrefix().getRatio()), 
											this.startingValue
												.getUnit()
												.getClass()
												.getConstructor(
														Prefix.class)
												.newInstance(
													this.startingValue
														.getUnit()
														.getPrefix())
											)));
				} else {
					railroad.addComponent(
							new RailRoadComponent( 
									new UnitValue(
											(this.startingValue.getUnit().getPrefix().getRatio()), 
											this.startingValue.getUnit().getClass().getConstructor(Prefix.class).newInstance(this.startingValue.getUnit().getPrefix())
											), 
									new UnitValue(
											1, 
											this.startingValue.getUnit().getClass().newInstance()
											)));
				}
			} catch (InstantiationException e) {
				throw new InvalidUnitException(e);
			} catch (IllegalAccessException e) {
				throw new InvalidUnitException(e);
			}catch (InvocationTargetException e) {
				throw new InvalidUnitException(e);
			} catch (NoSuchMethodException e) {
				throw new InvalidUnitException(e);
			}
		}
		
		//Convert Unit to Grams
		
		
		//If not in moles, convert units of value from grams to moles.
		if (!(this.startingValue.getUnit() instanceof MoleUnit)) {
			railroad.addComponent(
					new RailRoadComponent(
							new ChemicalValue(
									1, 
									new MoleUnit(), 
									this.startingValue.getChemical(),
									this.startingValue.getState()
									), 
							new ChemicalValue(
									this.startingValue.getChemical().getMolarMass(), 
									this.startingValue.getUnit(), 
									this.startingValue.getChemical(),
									this.startingValue.getState())));
		}
		
		//Convert to new chemical
		double[] conversion = this.equation.getRatioBetween(chemical, this.startingValue.getChemical());
		railroad.addComponent(new RailRoadComponent(
				new ChemicalValue(conversion[0], new MoleUnit(), chemical, new_chemical.getState()), 
				new ChemicalValue(conversion[1], new MoleUnit(), this.startingValue.getChemical(), this.startingValue.getState())));
		
		
		
		//If not in the desired units, convert units from moles to 'units'
		if (!unit.isInstance(new MoleUnit().getClass())) {
			//Only unit is 'grams', so convert to that
			try {
				railroad.addComponent(
						new RailRoadComponent(
								new ChemicalValue(
										chemical.getMolarMass(), 
										unit.getConstructor().newInstance(), 
										chemical, 
										new_chemical.getState()
										),
								new ChemicalValue(
										1, 
										new MoleUnit(), 
										chemical,
										new_chemical.getState()
										)));
			} catch (InstantiationException e) {
				throw new InvalidUnitException(e);
			} catch (IllegalAccessException e) {
				throw new InvalidUnitException(e);
			} catch (InvocationTargetException e) {
				throw new InvalidUnitException(e);
			} catch (NoSuchMethodException e) {
				throw new InvalidUnitException(e);
			}
		}
		
		return railroad;
	}
	
}
