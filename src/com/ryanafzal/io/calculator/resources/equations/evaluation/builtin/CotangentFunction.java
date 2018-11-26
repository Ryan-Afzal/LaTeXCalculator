package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;

public class CotangentFunction extends NumericalFunction {

	public CotangentFunction() {
		super("cot", new String[] {"x"}, "1/tan(x)");
	}
	
}
