package com.ryanafzal.io.calculator.resources.units.prefix;

public enum Prefix {

	// p > 1
	TERRA("terra", "T", 1000000000000.0),
	GIGA("giga", "G", 1000000000.0),
	MEGA("mega", "M", 1000000.0),
	KILO("kilo", "k", 1000.0),
	HECTA("hecta", "h", 100.0),
	DECA("deca", "D", 10.0),
	
	// No Prefix
	NONE("", "", 1),
	
	// p < 1
	DECI("deci", "d", 0.1),
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

