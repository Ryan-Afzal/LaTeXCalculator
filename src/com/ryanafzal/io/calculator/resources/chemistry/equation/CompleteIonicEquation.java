package com.ryanafzal.io.calculator.resources.chemistry.equation;

import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class CompleteIonicEquation extends ChemicalEquation {
	
	public CompleteIonicEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		super(reactants, products);
	}
	
	public NetIonicEquation getNetIonicEquation() {
		return null;
	}
	
}
