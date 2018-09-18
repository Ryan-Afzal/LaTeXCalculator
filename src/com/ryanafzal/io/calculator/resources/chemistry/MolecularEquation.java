package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class MolecularEquation extends ChemicalEquation {

	public MolecularEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		super(reactants, products);
	}
	
	public static MolecularEquation getChemicalEquationFromReactants(ChemicalValue[] reactants) {
		return null;
	}
	
	public static CompleteIonicEquation getCompleteIonicEquation() {
		return null;
	}
		
}
