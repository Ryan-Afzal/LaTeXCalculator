package com.ryanafzal.io.calculator.resources.equations.functions.builtin;

import com.ryanafzal.io.calculator.resources.equations.functions.NumericalFunction;

public class SecantFunction extends NumericalFunction {

	public SecantFunction() {
		super("sec", new String[] {"x"}, "1/cos(x)");
	}

}
