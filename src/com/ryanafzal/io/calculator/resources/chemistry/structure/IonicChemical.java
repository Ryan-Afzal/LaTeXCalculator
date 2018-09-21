package com.ryanafzal.io.calculator.resources.chemistry.structure;

import java.util.Arrays;
import java.util.HashMap;

//TODO Everything
public class IonicChemical implements IChemical {
	
	private IChemical[] cations;
	private IChemical[] anions;
	
	public IonicChemical(IChemical[] cations, IChemical[] anions) {
		String cation_name = cations[0].getIUPACName();
		String anion_name = anions[0].getIUPACName();
		
		for (int i = 0; i < cations.length; i++) {
			if (!cations[i].getIUPACName().equals(cation_name)) {
				throw new IllegalArgumentException("Cations must be of the same chemical type.");
			}
		}
		
		for (int i = 0; i < anions.length; i++) {
			if (!anions[i].getIUPACName().equals(anion_name)) {
				throw new IllegalArgumentException("Anions must be of the same chemical type.");
			}
		}
		
		double cation_sum = Arrays.asList(cations).stream().map(IChemical::getCharge).mapToInt(num -> num).sum();
		double anion_sum = Arrays.asList(anions).stream().map(IChemical::getCharge).mapToInt(num -> num).sum();
		
		if ((cation_sum + anion_sum) != 0) {
			throw new IllegalArgumentException("Ionic Compounds must have a net charge of 0.");
		}
		
		this.cations = cations;
		this.anions = anions;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		double mass = 0;
		
		for (int i = 0; i < this.cations.length; i++) {
			mass += (this.cations[i].getMolarMass());
		}
		
		for (int i = 0; i < this.anions.length; i++) {
			mass += (this.anions[i].getMolarMass());
		}
		
		return mass;
	}

	@Override
	public String getMolecularFormula() {
		String output = "";
		
		HashMap<String, Integer> cation_list = new HashMap<String, Integer>();
		HashMap<String, Integer> anion_list = new HashMap<String, Integer>();
		
		for (int i = 0; i < this.cations.length; i++) {
			String formula = this.cations[i].getMolecularFormula();
			
			if (cation_list.containsKey(formula)) {
				cation_list.put(formula, cation_list.get(formula) + 1);
			} else {
				cation_list.put(formula, 1);
			}
		}
		
		for (int i = 0; i < this.anions.length; i++) {
			String formula = this.anions[i].getMolecularFormula();
			
			if (anion_list.containsKey(formula)) {
				anion_list.put(formula, anion_list.get(formula) + 1);
			} else {
				anion_list.put(formula, 1);
			}
		}
		
		for (String cation_key : cation_list.keySet()) {
			int value = cation_list.get(cation_key);
			
			output += cation_key;
			if (value <= 1) {
				output += "_" + value;
			}
		}
		
		for (String anion_key : anion_list.keySet()) {
			int value = anion_list.get(anion_key);
			
			output += anion_key;
			
			if (value <= 1) {
				output += (anion_key + "_" + value);
			}
		}
		
		return output;
	}

	@Override
	public String getEmpiricalFormula() {
		return this.getMolecularFormula();
	}

	@Override
	public String getIUPACName() {
		return "Unnamed Ionic Compound";
	}

	@Override
	public int getCharge() {
		return 0;
	}

}
