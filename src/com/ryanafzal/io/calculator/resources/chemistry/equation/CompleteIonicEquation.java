package com.ryanafzal.io.calculator.resources.chemistry.equation;

import java.util.Arrays;
import java.util.List;

import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class CompleteIonicEquation extends ChemicalEquation {
	
	protected CompleteIonicEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		super(reactants, products);
	}
	
	public NetIonicEquation getNetIonicEquation() {
		List<ChemicalValue> reactants = Arrays.asList(this.reactants);
		List<ChemicalValue> products = Arrays.asList(this.products);
		
		for (int i = 0; i < reactants.size(); i++) {
			ChemicalValue reactant = reactants.get(i);
			
			if (products.contains(reactant)) {
				reactants.remove(reactant);
				products.remove(reactant);
			}
		}
		
		return new NetIonicEquation(reactants.toArray(new ChemicalValue[] {}), products.toArray(new ChemicalValue[] {}));
	}
	
}
