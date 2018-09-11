package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoadComponent;
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
	
	public static RailRoadComponent getConversionToPrefix(Unit starting_unit, Prefix prefix) {
		
		if (starting_unit.getPrefix().getRatio() < prefix.getRatio()) {
			return new RailRoadComponent(
				new UnitValue(
						1, 
						starting_unit.clone(prefix)//Unit with new prefix
						),
				new UnitValue(
						(prefix.getRatio() / starting_unit.getPrefix().getRatio()), 
						starting_unit.clone()//Unit with old prefix
						));
		} else {
			return new RailRoadComponent(
				new UnitValue(
						(starting_unit.getPrefix().getRatio() / prefix.getRatio()), 
						starting_unit.clone(prefix)//Unit with new prefix
						), 
				new UnitValue(
						1, 
						starting_unit.clone()//Unit with old prefix
						));
		}
	}
	
	public static RailRoadComponent getConversionToPrefix(UnitValue value, Prefix prefix) {
		return getConversionToPrefix(value.getUnit(), prefix);
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, " + this.getUnit().getLaTeXString();
	}
	
	@Override
	public String toString() {
		return super.toString() + "\\, " + this.getUnit().getLaTeXString();
	}

}
