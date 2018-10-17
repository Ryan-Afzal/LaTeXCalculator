package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;

public class CotangentFunction extends Function {

	public CotangentFunction() {
		super(new String[] {"x"}, "1/tan(x)");
	}
	
}
