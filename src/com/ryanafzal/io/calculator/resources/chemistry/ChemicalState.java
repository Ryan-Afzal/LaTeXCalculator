package com.ryanafzal.io.calculator.resources.chemistry;

public enum ChemicalState {
	
	SOLID("s"),
	LIQUID("l"),
	GAS("g"),
	AQUEOUS("aq");
	
	private String symbol;
	
	private ChemicalState(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
}
