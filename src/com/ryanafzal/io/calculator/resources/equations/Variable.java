package com.ryanafzal.io.calculator.resources.equations;

public class Variable {
	
	private String symbol;
	private IVariable value;
	
	public Variable(String symbol, IVariable value) {
		this.symbol = symbol;
		this.value = value;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public IVariable getValue() {
		return this.value;
	}
	
	public void setValue(IVariable value) {
		this.value = value;
	}

}
