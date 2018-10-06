package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
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
		String string = this.getChemical().getLaTeXString();
		
		if (Character.isAlphabetic(string.charAt(string.length() - 1))) {
			return super.getLaTeXString() + "\\, " + string + "_(" + this.state.getSymbol() + ")";
		} else {
			return super.getLaTeXString() + "\\, " + string + "\\,_(" + this.state.getSymbol() + ")";
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + this.chemical.getIUPACName() + "" + this.state.getSymbol();
	}

}
