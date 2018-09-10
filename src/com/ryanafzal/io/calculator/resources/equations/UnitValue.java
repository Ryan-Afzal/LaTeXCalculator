package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class UnitValue extends Value {

	private Unit unit;
	
	public UnitValue(double value, Unit unit) {
		super(value);
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void convertToPrefix(Prefix new_prefix) {
		//Only convert if prefixes are different
		if (!new_prefix.equals(this.getUnit().getPrefix())) {
			double ratio = this.getUnit().getPrefix().getRatio() / new_prefix.getRatio();
			
			try {
				this.unit = unit.getClass().newInstance();
				this.setValue(this.getValue() * ratio);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
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
