package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;

public class CosecantFunction extends Function {

	public CosecantFunction() {
		super(new String[] {"x"}, "1/sin(x)");
	}

}
