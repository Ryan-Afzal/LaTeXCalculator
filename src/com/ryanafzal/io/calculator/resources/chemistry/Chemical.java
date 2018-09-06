package com.ryanafzal.io.calculator.resources.chemistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

/**
 * Stores data about a chemical, such as molar mass.
 * @author s-afzalr
 *
 */
public class Chemical implements ILaTeXValue {

	private Atom[] atoms;
	private Bond[] bonds;
	
	public Chemical(Atom[] atoms, Bond[] bonds) {
		this.atoms = atoms;
		this.bonds = bonds;
	}
	
	public double getMolarMass() {
		return Arrays.stream(this.atoms).map(Atom::getAtomicMass).mapToDouble(Double::doubleValue).sum();
	}
	
	public String getMolecularFormula() {
		String result = "";
		
		int carbon = 0;
		int hydrogen = 0;
		HashMap<String, Integer> otheratoms = new HashMap<String, Integer>();
		
		for (Atom atom : this.atoms) {
			if (atom.equals(Atom.CARBON)) {
				carbon++;
			} else if (atom.equals(Atom.HYDROGEN)) {
				hydrogen++;
			} else {
				String symbol = atom.getSymbol();
				if (!otheratoms.containsKey(symbol)) {
					otheratoms.put(symbol, 1);
				} else {
					otheratoms.put(symbol, otheratoms.get(symbol) + 1);
				}
			}
		}
		
		if (carbon > 0) {
			result += "C_" + carbon;
		}
		
		if (hydrogen > 0) {
			result += "H_" + hydrogen;
		}
		
		Set<String> keys = otheratoms.keySet();
		Set<String> new_keys = new TreeSet<String>(keys);
		
		for (String s : new_keys) {
			result += (s + "_" + otheratoms.get(s));
		}
		
		return result;
	}
	
	public String getEmpiricalFormula() {
		return null;
	}
	
	public String getCondensedFormula() {
		return null;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Chemical && ((Chemical) o).getLaTeXString().equals(this.getLaTeXString())) {
			return true;
		}
		return false;
	}
	
	public static Chemical makeChemicalFromFormula(String formula) {
		return null;
	}

}
