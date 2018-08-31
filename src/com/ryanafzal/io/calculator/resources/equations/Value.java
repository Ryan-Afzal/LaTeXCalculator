package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class Value implements ILaTeXValue {
	
	private double value;
	
	public Value(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getValue() + "";
	}
	
	@Override
	public String toString() {
		return this.getValue() + "";
	}
	
}
