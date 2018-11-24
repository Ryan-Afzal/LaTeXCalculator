package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.misc.ILaTeXValue;

public interface ISolvable extends ILaTeXValue {
	public Value solve();
}
