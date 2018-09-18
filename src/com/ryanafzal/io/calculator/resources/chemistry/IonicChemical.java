package com.ryanafzal.io.calculator.resources.chemistry;

public class IonicChemical implements IChemical {

	private Chemical cation;
	private Chemical anion;
	
	private int num_cation;
	private int num_anion;
	
	public IonicChemical() {
		
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		return (cation.getMolarMass() * num_cation) + (anion.getMolarMass() * num_anion);
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
