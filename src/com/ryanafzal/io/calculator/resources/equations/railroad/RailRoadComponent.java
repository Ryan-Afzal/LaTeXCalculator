package com.ryanafzal.io.calculator.resources.equations.railroad;

import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.misc.ILaTeXValue;

public class RailRoadComponent implements ILaTeXValue {
	
	private UnitValue numerator;
	private UnitValue denominator;
	
	public RailRoadComponent(UnitValue numerator, UnitValue denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public UnitValue getNumerator() {
		return this.numerator;
	}
	
	public UnitValue getDenominator() {
		return this.denominator;
	}

	@Override
	public String getLaTeXString() {
		return "\\frac{" + this.getNumerator().getLaTeXString() + "}{" + this.getDenominator().getLaTeXString() + "}";
	}

	@Override
	public boolean isMath() {
		return true;
	}
	
}
