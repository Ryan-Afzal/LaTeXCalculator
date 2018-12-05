package com.ryanafzal.io.calculator.resources.equations.evaluation;

import java.util.Iterator;

public enum FunctionOperator implements Expression {
	
	LOG("log", 1) {
		public Double evaluate(Iterator<Double> args) {
			return Math.log10(args.next());
		}
	},
	
	NATURALLOG("ln", 1) {
		public Double evaluate(Iterator<Double> args) {
			return Math.log(args.next());
		}
	};
	
	private String name;
	private int numargs;
	
	private FunctionOperator(String name, int numargs) {
		this.name = name;
		this.numargs = numargs;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumArgs() {
		return this.numargs;
	}
	
	public abstract Double evaluate(Iterator<Double> args);
	
	public static FunctionOperator getFunctionOperator(String name) {		
		for (FunctionOperator f : FunctionOperator.values()) {
			if (f.getName().equals(f)) {
				return f;
			}
		}
		
		throw new IllegalArgumentException("Function " + name + " does not exist");
	}

}
