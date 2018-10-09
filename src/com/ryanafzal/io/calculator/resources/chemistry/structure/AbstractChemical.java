package com.ryanafzal.io.calculator.resources.chemistry.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AbstractChemical implements IChemical {

	private Atom[] atoms;
	private int charge = 0;
	
	public AbstractChemical(Atom[] atoms) {
		this.atoms = atoms;
	}
	
	public static AbstractChemical getAbstractChemicalFromString(String string) {
		//Chadkladsflkfdsa;lkj12982987324897352
		// ([A-Z]{1}[a-z]{0,2}([1-9]{1}[0-9]*|))+
		
		if (!Character.isUpperCase(string.charAt(0))) {
			throw new IllegalArgumentException(string + " is not a valid chemical formula.");
		}
		
		HashMap<AtomType, Integer> atoms = new HashMap<>();
		
		String type = string.charAt(0) + "";
		String num = "";
		
		boolean type_or_num = true;
		
		for (int i = 1; i < string.length(); i++) {
			if (type_or_num && Character.isDigit(string.charAt(i))) {
				type_or_num = false;
			} else if (Character.isUpperCase(string.charAt(i))) {
				type_or_num = true;
				
				AtomType type_ = AtomType.getAtomTypeFromSymbol(type);
				int num_;
				
				if (num.equals("")) {
					num_ = 1;
				} else {
					num_ = Integer.parseInt(num);
				}
				
				atoms.put(type_, num_);
				
				type = "";
				num = "";
			}
			
			if (type_or_num) {
				type += ("" + string.charAt(i));
			} else {
				num += ("" + string.charAt(i));
			}
			
			if (i == string.length() - 1) {
				type_or_num = true;
				
				AtomType type_ = AtomType.getAtomTypeFromSymbol(type);
				int num_;
				
				if (num.equals("")) {
					num_ = 1;
				} else {
					num_ = Integer.parseInt(num);
				}
				
				atoms.put(type_, num_);
				
				type = "";
				num = "";
			}
		}
		
		ArrayList<Atom> atoms_ = new ArrayList<Atom>();
		
		Iterator<AtomType> it = atoms.keySet().iterator();
		while (it.hasNext()) {
			AtomType atom_t = it.next();
			int current = atoms.get(atom_t);
			for (int i = 0; i < current; i++) {
				atoms_.add(new Atom(atom_t, 0));
			}
		}
		
		return new AbstractChemical(atoms_.toArray(new Atom[] {}));
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
			result += ("^{" + this.charge + "}");
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
		String output = this.getMolecularFormula();
		
		if (this.charge != 0) {
			output += ("^" + this.charge);
		}
		
		return output;
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
	
	@Override
	public String toString() {
		return this.getMolecularFormula().replaceAll(" ", "_").replaceAll("_", "");
	}
	
}
