package com.ryanafzal.io.calculator.resources.equations.functions.builtin;

import com.ryanafzal.io.calculator.resources.equations.functions.NumericalFunction;

public class CotangentFunction extends NumericalFunction {

	public CotangentFunction() {
		super("cot", new String[] {"x"}, "1/tan(x)");
	}
	
}
