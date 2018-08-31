package com.ryanafzal.io.calculator.resources.equations;

public class UnitValue extends Value {

	private String units;
	
	public UnitValue(double value, String units) {
		super(value);
		this.units = units;
	}
	
	public String getUnits() {
		return this.units;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, \\textup{" + this.getUnits() + "}";
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.getUnits();
	}

}
