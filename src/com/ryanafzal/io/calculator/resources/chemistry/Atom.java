package com.ryanafzal.io.calculator.resources.chemistry;

public enum Atom {
	
	HYDROGEN("Hydrogen", "H", 1, 1.01),
	HELIUM("Helium", "Hi", 2, 4.003),
	LITHIUM("Lithium", "Li", 3, 6.941),
	BERYLLIUM("Beryllium", "Be", 4, 9.012),
	BORON("Boron", "B", 5, 10.81),
	CARBON("Carbon", "C", 6, 12.01);
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	
	private Atom(String name, String symbol, int number, double mass) {
		this.name = name;
		this.symbol = symbol;
		this.number = number;
		this.mass = mass;
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
