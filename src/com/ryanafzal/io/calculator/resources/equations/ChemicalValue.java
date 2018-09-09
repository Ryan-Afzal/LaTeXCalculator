package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.units.Unit;

/**
 * Represents a chemical value, for use in an equation.
 * @author s-afzalr
 *
 */
public class ChemicalValue extends UnitValue {

	private Chemical chemical;
	private ChemicalState state;
	
	public ChemicalValue(double value, Unit unit, Chemical chemical, ChemicalState state) {
		super(value, unit);
		this.chemical = chemical;
		this.state = state;
	}
	
	public Chemical getChemical() {
		return this.chemical;
	}
	
	public ChemicalState getState() {
		return this.state;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, " + this.getChemical().getLaTeXString() + "(_" + this.state.getSymbol() + ")";
	}

}
