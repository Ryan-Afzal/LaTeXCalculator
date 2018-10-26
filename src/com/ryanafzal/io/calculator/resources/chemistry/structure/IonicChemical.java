package com.ryanafzal.io.calculator.resources.chemistry.structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.units.MoleUnit;

//TODO Everything
@Deprecated
public class IonicChemical implements IChemical {
	
	private IChemical[] cations;
	private IChemical[] anions;
	private Bond[] bonds;
	
	public IonicChemical(IChemical[] cations, IChemical[] anions, Bond[] bonds) {
		double cation_sum = Arrays.asList(cations).stream().map(IChemical::getCharge).mapToInt(num -> num).sum();
		double anion_sum = Arrays.asList(anions).stream().map(IChemical::getCharge).mapToInt(num -> num).sum();
		
		if ((cation_sum + anion_sum) != 0) {
			throw new IllegalArgumentException("Ionic Compounds must have a net charge of 0.");
		}
		
		this.cations = cations;
		this.anions = anions;
		this.bonds = bonds;
	}
	
	public ChemicalValue[] splitAqueous() {		
		HashMap<IChemical, Integer> cations = new HashMap<IChemical, Integer>();
		HashMap<IChemical, Integer> anions = new HashMap<IChemical, Integer>();
		
		for (int i = 0; i < this.cations.length; i++) {
			IChemical cation = this.cations[i];
			
			if (cations.containsKey(cation)) {
				cations.put(cation, cations.get(cation) + 1);
			} else {
				cations.put(cation, 1);
			}
		}
		
		for (int i = 0; i < this.anions.length; i++) {
			IChemical anion = this.anions[i];
			
			if (anions.containsKey(anion)) {
				anions.put(anion, anions.get(anion) + 1);
			} else {
				anions.put(anion, 1);
			}
		}
		
		Set<ChemicalValue> output = new HashSet<ChemicalValue>();
		cations.putAll(anions);

		Iterator<IChemical> i = cations.keySet().iterator();
		
		while (i.hasNext()) {
			IChemical key = i.next();
			output.add(new ChemicalValue(cations.get(key), new MoleUnit(), key,  ChemicalState.AQUEOUS));
		}
		
		return output.toArray(new ChemicalValue[] {});
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
