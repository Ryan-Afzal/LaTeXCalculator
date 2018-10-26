package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Electron {
	
	private boolean isBonded;
	private Atom parent;
	private Atom bonded;
	
	public Electron(Atom parent) {
		this.isBonded = false;
		this.parent = parent;
		this.bonded = null;
	}
	
	public void bond(Atom other) {
		this.bonded = other;
		this.isBonded = true;
	}
	
	public void deBond() {
		this.bonded = null;
		this.isBonded = false;
	}
	
	public boolean isBonded() {
		return this.isBonded;
	}
	
	public Atom getBondedAtom() {
		return this.bonded;
	}
	
}
