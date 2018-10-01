package com.ryanafzal.io.calculator.resources.chemistry.equation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IonicChemical;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;

public class MolecularEquation extends ChemicalEquation {

	public MolecularEquation(ChemicalValue[] reactants, ChemicalValue[] products) {
		super(reactants, products);
	}
	
	public static MolecularEquation getChemicalEquationFromReactants(ChemicalValue[] reactants) {
		return null;
	}
	
	public CompleteIonicEquation getCompleteIonicEquation() throws EquationException {
		if (Arrays.stream(this.reactants).filter(reactant -> reactant.getChemical() instanceof IonicChemical).findFirst().orElse(null) != null) {
			throw new EquationException("There must only be ionic chemicals present to create an ionic equation.");
		}
		
		List<ChemicalValue[]> aqueous_split_reactants = Arrays.asList(this.reactants)
				.stream()
				.filter(value -> value.getState() == ChemicalState.AQUEOUS)
				.map(value -> ((IonicChemical) value.getChemical()).splitAqueous())
				.collect(Collectors.toList());
		
		List<ChemicalValue[]> aqueous_split_products = Arrays.asList(this.products)
				.stream()
				.filter(value -> value.getState() == ChemicalState.AQUEOUS)
				.map(value -> ((IonicChemical) value.getChemical()).splitAqueous())
				.collect(Collectors.toList());
		
		List<ChemicalValue> new_reactants = new ArrayList<ChemicalValue>();
		List<ChemicalValue> new_products = new ArrayList<ChemicalValue>();
		
		for (int i = 0; i < reactants.size(); i++) {
			new_reactants.addAll(null);
		}
		
		for (int i = 0; i < products.size(); i++) {
			
		}
		
		return null;
	}
		
}
