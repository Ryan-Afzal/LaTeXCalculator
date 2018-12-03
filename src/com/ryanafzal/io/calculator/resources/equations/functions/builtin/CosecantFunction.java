package com.ryanafzal.io.calculator.resources.equations.functions.builtin;

import com.ryanafzal.io.calculator.resources.equations.functions.NumericalFunction;

public class CosecantFunction extends NumericalFunction {

	public CosecantFunction() {
		super("csc", new String[] {"x"}, "1/sin(x)");
	}

}
