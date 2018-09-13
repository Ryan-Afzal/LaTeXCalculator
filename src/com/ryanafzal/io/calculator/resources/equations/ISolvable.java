package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public interface ISolvable extends ILaTeXValue {
	public Value solve();
}
