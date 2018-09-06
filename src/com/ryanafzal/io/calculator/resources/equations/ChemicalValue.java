package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.Units;
import com.ryanafzal.io.calculator.resources.chemistry.Chemical;

/**
 * Represents a chemical value, for use in an equation.
 * @author s-afzalr
 *
 */
public class ChemicalValue extends UnitValue {

	private Chemical chemical;
	
	public ChemicalValue(double value, Units units, Chemical chemical) {
		super(value, units);
		this.chemical = chemical;
	}
	
	public Chemical getChemical() {
		return this.chemical;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, " + this.getChemical().getLaTeXString();
	}

}
