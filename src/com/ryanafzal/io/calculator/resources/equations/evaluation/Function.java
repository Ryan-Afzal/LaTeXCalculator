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
			int index = output.indexOf(this.args[i]);
			while (index != -1) {
				output = 
						output.substring(0, index) + 
						args[i] + 
						output.substring(index + this.args[i].length());
				index = output.indexOf(this.args[i]);
			}
		}
		
		return "(" + output + ")";
	}
	
	public static Function getFunctionFromDeclaration(String signature, String body) throws IllegalArgumentException {
		signature = signature.replace(" ", "");
		body = body.replace(" ", "");
		
		String[] args = signature.substring(signature.indexOf("(") + 1, signature.indexOf(")")).split(",");
		
		return new Function(args, body);
	}
	
}
