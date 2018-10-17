package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;

public class SecantFunction extends Function {

	public SecantFunction() {
		super(new String[] {"x"}, "1/cos(x)");
	}

}
