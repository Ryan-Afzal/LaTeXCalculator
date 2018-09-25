package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs;

import com.ryanafzal.io.calculator.resources.chemistry.structure.Atom;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AtomType;

public class AtomConstruct implements SubstituentConstruct {

	private AtomType atom;
	private int charge;
	
	public AtomConstruct(AtomType atom, int charge) {
		this.atom = atom;
		this.charge = charge;
	}
	
	public Atom getAtomFromConstruct() {
		return new Atom(this.atom, this.charge);
	}
	
	@Override
	public AtomConstruct clone() {
		return new AtomConstruct(this.atom, this.charge);
	}

}
