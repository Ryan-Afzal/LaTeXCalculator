package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoadComponent;
import com.ryanafzal.io.calculator.resources.units.MoleUnit;
import com.ryanafzal.io.calculator.resources.units.QuantityUnit;
import com.ryanafzal.io.calculator.resources.units.Unit;

public class Stoichiometry {
	
	private ChemicalEquation equation;
	private ChemicalValue startingValue;
	
	public Stoichiometry(ChemicalEquation equation, ChemicalValue startingValue) {
		this.equation = equation;
		this.startingValue = startingValue;
	}
	
	public RailRoad solveFor(Chemical chemical, Class<? extends Unit> unit) throws EquationException {
		RailRoad railroad = new RailRoad(this.startingValue);
		
		//If not in moles, convert units of value from 'startingValue' to moles.
		if (!(this.startingValue.getUnit() instanceof MoleUnit)) {
			railroad.addComponent(new RailRoadComponent(new ChemicalValue(1, new MoleUnit(), this.startingValue.getChemical()), this.startingValue));
		}
		
		//Convert to new chemical
		double[] conversion = this.equation.getRatioBetween(chemical, this.startingValue.getChemical());
		railroad.addComponent(new RailRoadComponent(
				new ChemicalValue(conversion[0], new MoleUnit(), chemical), 
				new ChemicalValue(conversion[1], new MoleUnit(), this.startingValue.getChemical())));
		
		UnitValue solution = railroad.solveRailroad();
		ChemicalValue result = new ChemicalValue(solution.getValue(), solution.getUnit(), chemical);
		
		//If not in the desired units, convert units from moles to 'units'
		if (!unit.isInstance(result.getUnit())) {
			//Convert to unit
		}
		
		return railroad;
	}
	
}
