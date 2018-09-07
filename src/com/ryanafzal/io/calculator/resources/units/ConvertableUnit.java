package com.ryanafzal.io.calculator.resources.units;

public interface ConvertableUnit {
	public Unit convertTo(Class<? extends Unit> unit);
}
