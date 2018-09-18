package com.ryanafzal.io.calculator.resources.chemistry;

public class Atom implements IChemical {
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	
	public Atom(AtomType atom) {
		this.name = atom.getName();
		this.symbol = atom.getSymbol();
		this.number = atom.getAtomicNumber();
		this.mass = atom.getAtomicMass();
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
		// TODO NEEDS IMPLEMENTATION!!!!!!!!!!
		return 0;
	}
	
}
