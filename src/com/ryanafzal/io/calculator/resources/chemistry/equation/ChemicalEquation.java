package com.ryanafzal.io.calculator.resources.chemistry.equation;

import java.util.ArrayList;
import java.util.Arrays;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.IVariable;

/**
 * Represents a chemical equation. 
 * @author s-afzalr
 *
 */
public abstract class ChemicalEquation implements ILaTeXValue, IVariable {

	protected ChemicalValue[] reactants;
	protected ChemicalValue[] products;
	
	/**
	 * Creates a new <tt>ChemicalEquation</tt> from the specified reactants and products. 
	 * 
	 * @param reactants An array of the reactants. Units must be <tt>MoleUnit</tt>!
	 * @param products An array of the products. Units must be <tt>MoleUnit</tt>!
	 */
	public ChemicalEquation(ChemicalValue[] reactants, ChemicalValue[] products) {		
		this.reactants = reactants;
		this.products = products;
	}
	
	public double[] getRatioBetween(IChemical numerator, IChemical denominator) throws EquationException {
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
	
	public ChemicalValue getChemicalValue(IChemical chemical) throws EquationException {
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
		String output = this.reactants[0].getLaTeXString();
		
		for (int i = 1; i < this.reactants.length; i++) {
			output += (" + " + this.reactants[i].getLaTeXString());
		}
		
		output += (" -> " + this.products[0].getLaTeXString());
		
		for (int i = 1; i < this.products.length; i++) {
			output += this.products[i].getLaTeXString();
		}
		
		return output;
	}
	
	@Override
	public boolean isMath() {
		return true;
	}
	
}
