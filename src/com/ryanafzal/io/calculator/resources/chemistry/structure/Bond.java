package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Bond {
	
	private Atom atom1;
	private BondType bond;
	private Atom atom2;
	
	public Bond(Atom atom1, BondType bond, Atom atom2) {
		
	}
	
	public Atom getAtom1() {
		return this.atom1;
	}
	
	public Atom getAtom2() {
		return this.atom2;
	}
	
	public BondType getBond() {
		return this.bond;
	}
	
}
