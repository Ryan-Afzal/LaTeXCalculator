package com.ryanafzal.io.calculator.resources.equations;

public class ExperimentVariable extends Variable {

	public ExperimentVariable(String symbol, IVariable value) {
		super(symbol, value);
	}
	
	@Override
	public boolean deletable() {
		return false;
	}
	
}
