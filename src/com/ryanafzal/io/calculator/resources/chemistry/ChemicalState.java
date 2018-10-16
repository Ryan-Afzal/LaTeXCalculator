package com.ryanafzal.io.calculator.resources.chemistry;

import java.util.Arrays;

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
	
	public static ChemicalState getStateFromString(String string) {
		return Arrays.asList(ChemicalState.values())
				.stream()
				.filter(prefix -> prefix.getSymbol().equals(string))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(string + " is not a valid chemical state"));
	}
	
}
