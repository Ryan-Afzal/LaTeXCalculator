package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class ChemicalEquation implements ILaTeXValue {
	
	private ChemicalValue[] reactants;
	private ChemicalValue[] products;
	
	public ChemicalEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		this.reactants = reactants;
		this.products = products;
	}
	
	public double[] getRatioBetween(Chemical numerator, Chemical denominator) {
		
		
		double ratio_num;
		double ratio_den;
		
		return null;
	}
	
	@Override
	public String getLaTeXString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}