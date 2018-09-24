package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs;

import com.ryanafzal.io.calculator.resources.chemistry.structure.Atom;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Bond;
import com.ryanafzal.io.calculator.resources.chemistry.structure.BondType;

public class BondConstruct {
	
	/**
	 * The index of the first atom.
	 */
	private int atom1;
	private BondType bond;
	/**
	 * The index of the second atom.
	 */
	private int atom2;
	
	public BondConstruct(int atom1, BondType bond, int atom2) {
		this.atom1 = atom1;
		this.bond = bond;
		this.atom2 = atom2;
	}
	
	public int getAtom1() {
		return this.atom1;
	}
	
	public int getAtom2() {
		return this.atom2;
	}
	
	public void setAtom1(int atom1) {
		this.atom1 = atom1;
	}
	
	public void setAtom2(int atom2) {
		this.atom2 = atom2;
	}
	
	public Bond getBondFromConstruct(Atom[] atoms) {
		return new Bond(atoms[this.atom1], this.bond, atoms[this.atom2]);
	}
	
}
