package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

/**
 * Meters
 * @author s-afzalr
 *
 */
public class LengthUnit extends Unit {

	public LengthUnit(Prefix prefix) {
		super(prefix);
	}
	
	public LengthUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "meters";
	}

	@Override
	public String getSymbol() {
		return "m";
	}

}
