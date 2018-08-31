package com.ryanafzal.io.calculator.resources.equations;

public class ChemicalValue extends UnitValue {

	private String chemical;
	
	public ChemicalValue(double value, String units, String chemical) {
		super(value, units);
		this.chemical = chemical;
	}
	
	public String getChemical() {
		return this.chemical;
	}
	
	@Override
	public String getLaTeXString() {
		return super.getLaTeXString() + "\\, " + this.getChemical();
	}

}
