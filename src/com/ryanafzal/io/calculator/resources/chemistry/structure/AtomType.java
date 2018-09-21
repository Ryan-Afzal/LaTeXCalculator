package com.ryanafzal.io.calculator.resources.chemistry.structure;

public enum AtomType {
	
	HYDROGEN("Hydrogen", "H", 1, 1.0079),
	HELIUM("Helium", "Hi", 2, 4.0026),
	LITHIUM("Lithium", "Li", 3, 6.941),
	BERYLLIUM("Beryllium", "Be", 4, 9.0122),
	BORON("Boron", "B", 5, 10.811),
	CARBON("Carbon", "C", 6, 12.0107),
	NITROGEN("Nitrogen", "N", 7, 14.0067),
	OXYGEN("Oxygen", "O", 8, 15.9994),
	FLUORINE("Fluorine", "F", 9, 18.9984),
	NEON("Neon", "Ne", 10, 20.1797),
	SODIUM("Sodium", "Na", 11, 22.9897),
	MAGNESIUM("Magnesium", "Mg", 12, 24.305),
	ALUMINUM("Aluminum", "Al", 13, 26.9815),
	SILICON("Silicon", "Si", 14, 28.0855),
	PHOSPHORUS("Phosphorus", "P", 15, 30.9738),
	SULFUR("Sulfur", "S", 16, 32.065),
	CHLORINE("Chlorine", "Cl", 17, 35.453),
	ARGON("Argon", "Ar", 18, 39.0983);
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	
	private AtomType(String name, String symbol, int number, double mass) {
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
