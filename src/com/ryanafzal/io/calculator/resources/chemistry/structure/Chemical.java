package com.ryanafzal.io.calculator.resources.chemistry.structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Stores data about a chemical, such as molar mass.
 * @author s-afzalr
 *
 */
public class Chemical implements IChemical {
	
	private Atom[] atoms;
	private Bond[] bonds;
	
	private int charge = 0;
	
	public Chemical(Atom[] atoms, Bond[] bonds, int charge) {
		this.atoms = atoms;
		this.bonds = bonds;
		this.charge = charge;
	}
	
	@Override
	public double getMolarMass() {
		return Arrays.stream(this.atoms).map(Atom::getAtomicMass).mapToDouble(Double::doubleValue).sum();
	}

	@Override
	public String getMolecularFormula() {
		String result = "";
		
		int carbon = 0;
		int hydrogen = 0;
		HashMap<String, Integer> otheratoms = new HashMap<String, Integer>();
		
		for (Atom atom : this.atoms) {
			if (atom.equals(AtomType.CARBON)) {
				carbon++;
			} else if (atom.equals(AtomType.HYDROGEN)) {
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
			if (carbon > 1) {
				result += " C_" + carbon;
			} else {
				result += " C";
			}
		}
		
		if (hydrogen > 0) {
			if (hydrogen > 1) {
				result += " H_" + hydrogen;
			} else {
				result += " H";
			}
		}
		
		Set<String> keys = otheratoms.keySet();
		Set<String> new_keys = new TreeSet<String>(keys);
		
		for (String s : new_keys) {
			if (otheratoms.get(s) > 1) {
				result += (" " + s + "_" + otheratoms.get(s));
			} else {
				result += (" " + s);
			}
		}
		
		if (this.charge != 0) {
			result += ("^" + this.charge);
		}
		
		return result;
	}

	//TODO
	@Override
	public String getEmpiricalFormula() {
		return null;
	}

	//TODO
	@Override
	public String getIUPACName() {
		return this.getMolecularFormula();
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof IChemical && ((IChemical) o).getLaTeXString().equals(this.getLaTeXString())) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isMath() {
		return true;
	}

	@Override
	public int getCharge() {
		return this.charge;
	}

}
