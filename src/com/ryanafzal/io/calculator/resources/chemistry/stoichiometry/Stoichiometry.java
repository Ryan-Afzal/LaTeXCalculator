package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;

public class Stoichiometry {
	
	private ChemicalEquation equation;
	private ChemicalValue startingValue;
	
	public Stoichiometry(ChemicalEquation equation, ChemicalValue startingValue) {
		this.equation = equation;
		this.startingValue = startingValue;
	}
	
	public RailRoad solveFor(Chemical chemical, String units) {
		return null;
	}
	
}
