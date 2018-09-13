package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class LaTeXEquation implements ISolvable {

	private ISolvable[] parts;
	
	public LaTeXEquation() {
		
	}
	
	@Override
	public UnitValue solve() {
		return null;
	}
	
	@Override
	public String getLaTeXString() {
		String output = "";
		
		for (ILaTeXValue part : parts) {
			output += (part.getLaTeXString() + "\\linebreak");
		}
		
		return output;
	}

}
