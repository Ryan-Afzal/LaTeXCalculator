package com.ryanafzal.io.calculator.resources.units.prefix;

public enum Prefix {

	CENTI("centi", "c", 0.01),
	MILLI("milli", "m", 0.001);
	
	private String prefix;
	private String symbol;
	private double ratio;
	
	private Prefix(String prefix, String symbol, double ratio) {
		this.prefix = prefix;
		this.symbol = symbol;
		this.ratio = ratio;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public double getRatio() {
		return this.ratio;
	}
	
}

