package com.ryanafzal.io.calculator.resources.chemistry;

//TODO Everything
public class IonicChemical implements IChemical {
	
	/*
	 * {
	 * 	{chemicals}
	 *  {cooefficients}
	 * }
	 */
	private Chemical[] cations;
	private Chemical[] anions;
	
	public IonicChemical(Chemical[] cations, Chemical[] anions) {
		this.cations = cations;
		this.anions = anions;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Override
	public double getMolarMass() {
		double mass = 0;
		
		for (int i = 0; i < cations.length; i++) {
			mass += (cations[i].getMolarMass());
		}
		
		for (int i = 0; i < anions.length; i++) {
			mass += (anions[i].getMolarMass());
		}
		
		return mass;
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
