package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

/**
 * Stores data about a chemical, such as molar mass.
 * @author s-afzalr
 *
 */
public class Chemical implements ILaTeXValue {

	
	
	public Chemical() {
		
	}
	
	public double getMolarMass() {
		return 0.0;
	}
	
	@Override
	public String getLaTeXString() {
		return null;
	}

}
