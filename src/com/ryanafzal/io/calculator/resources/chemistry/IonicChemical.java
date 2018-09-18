package com.ryanafzal.io.calculator.resources.chemistry;

//TODO Everything
public class IonicChemical implements IChemical {
	
	public IonicChemical() {
		
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		return 0.0;
	}

	@Override
	public String getMolecularFormula() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmpiricalFormula() {
		return this.getMolecularFormula();
	}

	@Override
	public String getIUPACName() {
		return "Unnamed Ionic Compound";
	}

	@Override
	public int getCharge() {
		return 0;
	}

}
