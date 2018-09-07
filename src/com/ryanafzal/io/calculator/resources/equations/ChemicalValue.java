package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.units.Unit;

/**
 * Represents a chemical value, for use in an equation.
 * @author s-afzalr
 *
 */
public class ChemicalValue extends UnitValue {

	private Chemical chemical;
	
	public ChemicalValue(double value, Unit unit, Chemical chemical) {
		super(value, unit);
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
