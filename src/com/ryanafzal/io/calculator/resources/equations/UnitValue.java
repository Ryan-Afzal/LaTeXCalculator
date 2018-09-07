package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.units.Unit;

public class UnitValue extends Value {

	private Unit unit;
	
	public UnitValue(double value, Unit unit) {
		super(value);
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, \\textup{" + this.getUnit().getLaTeXString() + "}";
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.getUnit().getLaTeXString();
	}

}
