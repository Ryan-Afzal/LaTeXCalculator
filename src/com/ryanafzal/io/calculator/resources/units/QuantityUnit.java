package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

/**
 * Grams
 * @author s-afzalr
 *
 */
public class QuantityUnit extends Unit {
	
	public QuantityUnit(Prefix prefix) {
		super(prefix);
	}
	
	public QuantityUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "grams";
	}

	@Override
	public String getSymbol() {
		return "g";
	}

}
