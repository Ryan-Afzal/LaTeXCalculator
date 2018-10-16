package com.ryanafzal.io.calculator.resources.equations.evaluation;

import com.ryanafzal.io.calculator.resources.equations.IVariable;

//TODO
public class Function implements IVariable {
	
	private String[] args;
	private String expression;
	
	public Function(String[] args, String expression) {
		this.args = args;
		this.expression = expression;
	}
	
	public String evaluate(String[] args) {
		String output = this.expression;
		
		for (int i = 0; i < args.length; i++) {
			output.replace(this.args[i], args[i]);
		}
		
		return "(" + output + ")";
	}
	
	public static Function getFunctionFromDeclaration(String signature, String body) throws IllegalArgumentException {
		
		
		return null;
	}
	
}
