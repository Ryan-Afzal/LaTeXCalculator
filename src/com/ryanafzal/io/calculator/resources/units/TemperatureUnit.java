package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class TemperatureUnit extends Unit {

	public TemperatureUnit(Prefix prefix) {
		super(prefix);
	}
	
	public TemperatureUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "Kelvin";
	}

	@Override
	public String getSymbol() {
		return "K";
	}

}
