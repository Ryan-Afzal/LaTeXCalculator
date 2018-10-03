package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Bond {
	
	private IChemical atom1;
	private BondType bond;
	private IChemical atom2;
	
	public Bond(IChemical atom1, BondType bond, IChemical atom2) {
		this.atom1 = atom1;
		this.bond = bond;
		this.atom2 = atom2;
	}
	
	public IChemical getIChemical1() {
		return this.atom1;
	}
	
	public IChemical getIChemical2() {
		return this.atom2;
	}
	
	public BondType getBond() {
		return this.bond;
	}
	
}
