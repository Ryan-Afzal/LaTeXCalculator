package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

/**
 * Stores data about a chemical, such as molar mass.
 * @author s-afzalr
 *
 */
public class Chemical implements ILaTeXValue {

	private int[] atoms;
	private Bond[] bonds;
	
	public Chemical(int[] atoms, Bond[] bonds) {
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
		
		int[] atoms = {
				1, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6
		};
		Bond[] bonds = {
				new Bond(0, BondType.SINGLE, 8),
				new Bond(1, BondType.SINGLE, 8),
				new Bond(2, BondType.SINGLE, 8),
				new Bond(3, BondType.SINGLE, 9),
				new Bond(4, BondType.SINGLE, 9),
				new Bond(5, BondType.SINGLE, 10),
				new Bond(6, BondType.SINGLE, 10),
				new Bond(7, BondType.SINGLE, 10),
				new Bond(8, BondType.SINGLE, 9),
				new Bond(9, BondType.SINGLE, 10)
		};
		
		Chemical propane = new Chemical(atoms, bonds);
		System.out.println(propane.getLaTeXString());
	}

}
