package com.ryanafzal.io.calculator.resources.equations.evaluation;

//TODO
public class Function {
	
	private String[] args;
	private String expression;
	
	public Function(String expression, String[] args) {
		this.args = args;
		this.expression = expression;
	}
	
	public double evaluate(double[] args) {
		return 0.0;
	}
	
}
