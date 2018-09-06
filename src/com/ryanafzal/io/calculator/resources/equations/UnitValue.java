package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.Units;

public class UnitValue extends Value {

	private Units units;
	
	public UnitValue(double value, Units units) {
		super(value);
		this.units = units;
	}
	
	public Units getUnits() {
		return this.units;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, \\textup{" + this.getUnits().getValue() + "}";
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.getUnits().getValue();
	}

}
