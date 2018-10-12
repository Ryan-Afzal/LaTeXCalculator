package com.ryanafzal.io.calculator.resources.chemistry.structure;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public interface IChemical extends ILaTeXValue {
	public double getMolarMass();
	public String getMolecularFormula();
	public String getEmpiricalFormula();
	public String getIUPACName();
	public int getCharge();
}
