package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class MoleUnit extends Unit {

	public MoleUnit(Prefix prefix) {
		super(prefix);
	}
	
	public MoleUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "moles";
	}

	@Override
	public String getSymbol() {
		return "mol";
	}

}
