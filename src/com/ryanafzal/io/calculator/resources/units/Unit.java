package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public abstract class Unit implements ILaTeXValue {
	
	private Prefix prefix;
	
	public Unit(Prefix prefix) {
		this.prefix = prefix;
	}
	
	public Prefix getPrefix() {
		return this.prefix;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getPrefix().getSymbol() + this.getSymbol();
	}
	
	public abstract String getName();
	public abstract String getSymbol();
	
}
