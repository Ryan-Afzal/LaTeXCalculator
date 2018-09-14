package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class LaTeXBlock implements ISolvable {

	private ILaTeXValue[] parts;
	private UnitValue value;
	
	public LaTeXBlock(ILaTeXValue[] parts, UnitValue value) {
		this.parts = parts;
		this.value = value;
	}
	
	public LaTeXBlock(ILaTeXValue[] parts) {
		this(parts, null);
	}
	
	public ILaTeXValue getPart(int i) {
		return this.parts[i];
	}
	
	@Override
	public String getLaTeXString() {
		String output = "\\[";
		
		for (int i = 0; i < this.parts.length; i++) {
			if (this.parts[i].isMath()) {
				output += ("$$" + this.parts[i].getLaTeXString() + "$$ ");
			} else {
				output += this.parts[i].getLaTeXString();
			}
		}
		
		return output + "\\]";
	}
	
	@Override
	public boolean isMath() {
		return false;
	}
	
	@Override
	public UnitValue solve() {
		return this.value;
	}

}
