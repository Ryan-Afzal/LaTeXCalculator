package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;

public class FactorialFunction extends Function {

	public FactorialFunction() {
		super(new String[] {"x"}, "!(x)");
	}
	
	@Override
	public String evaluate(String[] args) {
		int num = Integer.parseInt(args[0]);
		
		if (num < 0) {
			throw new IllegalArgumentException("A factorial is only defined for non-negative integers.");
		}
		
		int total = 1;
		for (int i = 1; i <= num; i++) {
			total *= i;
		}
		
		return total + "";
	}

}
