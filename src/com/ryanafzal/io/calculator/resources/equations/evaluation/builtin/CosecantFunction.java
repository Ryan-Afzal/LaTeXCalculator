package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;

public class CosecantFunction extends NumericalFunction {

	public CosecantFunction() {
		super(new String[] {"x"}, "1/sin(x)");
	}

}
