package com.ryanafzal.io.calculator.resources.chemistry;

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
		return 0.0;
	}
	
	@Override
	public String getLaTeXString() {
		return null;
	}
	
	public static Chemical makeChemicalFromFormula(String formula) {
		return null;
	}
	
	public static void main(String[] args) {
		/* How will the atoms be stored? Objects/Strings/Integers? */
		
		Atom[] atoms = {
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.HYDROGEN,
				Atom.CARBON,
				Atom.CARBON,
				Atom.CARBON
		};
		Bond[] bonds = {
				new Bond(atoms[0], BondType.SINGLE, atoms[8]),
				new Bond(atoms[1], BondType.SINGLE, atoms[8]),
				new Bond(atoms[2], BondType.SINGLE, atoms[8]),
				new Bond(atoms[3], BondType.SINGLE, atoms[9]),
				new Bond(atoms[4], BondType.SINGLE, atoms[9]),
				new Bond(atoms[5], BondType.SINGLE, atoms[10]),
				new Bond(atoms[6], BondType.SINGLE, atoms[10]),
				new Bond(atoms[7], BondType.SINGLE, atoms[10]),
				new Bond(atoms[8], BondType.SINGLE, atoms[9]),
				new Bond(atoms[9], BondType.SINGLE, atoms[10])
		};
		
		Chemical propane = new Chemical(atoms, bonds);
		System.out.println(propane.getLaTeXString());
	}

}
