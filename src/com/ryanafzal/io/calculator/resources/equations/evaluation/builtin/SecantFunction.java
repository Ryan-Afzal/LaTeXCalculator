package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;

public class SecantFunction extends NumericalFunction {

	public SecantFunction() {
		super(new String[] {"x"}, "1/cos(x)");
	}

}
