package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class ChemicalEquation implements ILaTeXValue {
	
	private Chemical[] reactants;
	private Chemical[] products;
	
	public ChemicalEquation(Chemical[] reactants, Chemical[] products) {
		this.reactants = reactants;
		this.products = products;
	}
	
	@Override
	public String getLaTeXString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
