package com.ryanafzal.io.calculator.resources.equations.functions;

public class NumericalFunction extends Function {
	
	private String name;
	
	public NumericalFunction(String name, String[] args, String expression) {
		super(args, expression);
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name + "=" + this.getExpression();
	}

}
