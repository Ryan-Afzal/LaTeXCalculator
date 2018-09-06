package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import com.ryanafzal.io.calculator.resources.Units;
import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoadComponent;

public class Stoichiometry {
	
	private ChemicalEquation equation;
	private ChemicalValue startingValue;
	
	public Stoichiometry(ChemicalEquation equation, ChemicalValue startingValue) {
		this.equation = equation;
		this.startingValue = startingValue;
	}
	
	public RailRoad solveFor(Chemical chemical, Units units) throws EquationException {
		RailRoad railroad = new RailRoad(startingValue);
		
		//Convert units of value from 'startingValue' to moles
		double[] conversion = this.equation.getRatioBetween(chemical, this.startingValue.getChemical());
		railroad.addComponent(new RailRoadComponent(
				new ChemicalValue(conversion[0], Units.MOLES, chemical), 
				new ChemicalValue(conversion[1], Units.MOLES, this.startingValue.getChemical())));
		
		//Convert units from moles to 'units'
		
		return railroad;
	}
	
}
