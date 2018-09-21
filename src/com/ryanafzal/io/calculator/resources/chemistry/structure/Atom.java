package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Atom implements IChemical {
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	private int charge;
	
	public Atom(AtomType atom, int charge) {
		this.name = atom.getName();
		this.symbol = atom.getSymbol();
		this.number = atom.getAtomicNumber();
		this.mass = atom.getAtomicMass();
		this.charge = charge;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public int getAtomicNumber() {
		return this.number;
	}
	
	public double getAtomicMass() {
		return this.mass;
	}

	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		return this.getAtomicMass();
	}

	@Override
	public String getMolecularFormula() {
		return this.getSymbol();
	}

	@Override
	public String getEmpiricalFormula() {
		return this.getMolecularFormula();
	}

	@Override
	public String getIUPACName() {
		return this.getMolecularFormula();
	}

	@Override
	public int getCharge() {
		return this.charge;
	}
	
}
