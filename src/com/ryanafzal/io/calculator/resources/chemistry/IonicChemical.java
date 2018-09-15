package com.ryanafzal.io.calculator.resources.chemistry;

import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;

public class IonicChemical implements IChemical {

	private Chemical cation;
	private Chemical anion;
	
	public IonicChemical(ChemicalValue cation, ChemicalValue anion) {
		if (cation.getChemical().getCharge() < 1 || anion.getChemical().getCharge() > -1) {
			throw new IllegalArgumentException();
		}
		
		this.cation = (Chemical) cation.getChemical();
		this.anion = (Chemical) anion.getChemical();
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		// TODO Auto-generated method stub
		return 0;
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
