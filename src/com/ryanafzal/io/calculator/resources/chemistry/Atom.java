package com.ryanafzal.io.calculator.resources.chemistry;

public class Atom {
	
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
	
}
