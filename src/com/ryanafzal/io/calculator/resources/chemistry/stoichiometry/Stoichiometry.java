package com.ryanafzal.io.calculator.resources.chemistry.stoichiometry;

import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class Stoichiometry {
	
	private ChemicalEquation equation;
	private ChemicalValue startingValue;
	
	public Stoichiometry(ChemicalEquation equation, ChemicalValue startingValue) {
		this.equation = equation;
		this.startingValue = startingValue;
	}
	
}
