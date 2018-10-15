package com.ryanafzal.io.calculator.resources.equations.evaluation;

import com.ryanafzal.io.calculator.resources.equations.IVariable;

//TODO
public class Function {
	
	private String[] args;
	private String expression;
	
	public Function(String[] args, String expression) {
		this.args = args;
		this.expression = expression;
	}
	
	public double evaluate(IVariable[] args) {
		return 0.0;
	}
	
	public static Function getFunctionFromDeclaration(String signature, String body) throws IllegalArgumentException {
		
		
		return null;
	}
	
}
