package com.ryanafzal.io.calculator.resources.chemistry;

public class Bond {
	
	private int atom1;
	private BondType bond;
	private int atom2;
	
	public Bond(int atom1, BondType bond, int atom2) {
		
	}
	
	public int getAtom1() {
		return this.atom1;
	}
	
	public int getAtom2() {
		return this.atom2;
	}
	
	public BondType getBond() {
		return this.bond;
	}
	
}
