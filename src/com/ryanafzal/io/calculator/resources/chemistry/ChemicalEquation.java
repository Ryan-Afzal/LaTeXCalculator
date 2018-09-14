package com.ryanafzal.io.calculator.resources.chemistry;

import java.util.ArrayList;
import java.util.Arrays;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;

public class ChemicalEquation implements ILaTeXValue {
	
	private ChemicalValue[] reactants;
	private ChemicalValue[] products;
	
	public ChemicalEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		this.reactants = reactants;
		this.products = products;
	}
	
	public double[] getRatioBetween(Chemical numerator, Chemical denominator) throws EquationException {
		ArrayList<ChemicalValue> chemicals = new ArrayList<ChemicalValue>(Arrays.asList(this.reactants));
		chemicals.addAll(Arrays.asList(this.products));
		
		double[] result = new double[2];
		
		ChemicalValue num_value = chemicals.stream().filter(chemical -> chemical.getChemical().equals(numerator)).findFirst().orElse(null);
		ChemicalValue den_value = chemicals.stream().filter(chemical -> chemical.getChemical().equals(denominator)).findFirst().orElse(null);
		
		if (num_value == null) {
			throw new EquationException("Chemical " + numerator + " is not present in this equation.");
		} else if (den_value == null) {
			throw new EquationException("Chemical " + denominator + " is not present in this equation.");
		}
		
		result[0] = num_value.getValue();
		result[1] = den_value.getValue();
		
		return result;
	}
	
	public ChemicalValue getFirstProduct() {
		return this.products[0];
	}
	
	public ChemicalValue getChemicalValue(Chemical chemical) throws EquationException {
		ArrayList<ChemicalValue> chemicals = new ArrayList<ChemicalValue>(Arrays.asList(this.reactants));
		chemicals.addAll(Arrays.asList(this.products));
		
		ChemicalValue res = chemicals.stream().filter(c -> c.getChemical().equals(chemical)).findFirst().orElse(null);
		if (res == null) {
			throw new EquationException("Chemical " + chemical + " is not present in this equation.");
		}
		
		return res;
	}
	
	@Override
	public String getLaTeXString() {
		return "10";
	}

	@Override
	public boolean isMath() {
		return true;
	}
	
}
