package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.chemistry.IChemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.units.Unit;

/**
 * Represents a chemical value, for use in an equation.
 * @author s-afzalr
 *
 */
public class ChemicalValue extends UnitValue {

	private IChemical chemical;
	private ChemicalState state;
	
	public ChemicalValue(double value, Unit unit, IChemical chemical, ChemicalState state) {
		super(value, unit);
		this.chemical = chemical;
		this.state = state;
	}
	
	public IChemical getChemical() {
		return this.chemical;
	}
	
	public ChemicalState getState() {
		return this.state;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, " + this.getChemical().getLaTeXString() + "\\left(" + this.state.getSymbol() + "\\right)";
	}

}
