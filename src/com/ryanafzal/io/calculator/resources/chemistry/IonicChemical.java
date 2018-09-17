package com.ryanafzal.io.calculator.resources.chemistry;

public class IonicChemical implements IChemical {

	private Chemical cation;
	private Chemical anion;
	
	private int num_cation;
	private int num_anion;
	
	public IonicChemical(Chemical cation, int num_cation, Chemical anion, int num_anion) {
		if (cation.getCharge() < 1 || anion.getCharge() > -1 || num_cation <= 0 || num_anion <= 0) {
			throw new IllegalArgumentException();
		}

		this.cation = cation;
		this.anion = anion;
		this.num_cation = num_cation;
		this.num_anion = num_anion;
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
