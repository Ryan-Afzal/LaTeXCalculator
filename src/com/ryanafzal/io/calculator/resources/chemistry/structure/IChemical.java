package com.ryanafzal.io.calculator.resources.chemistry.structure;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.equations.IVariable;

public interface IChemical extends ILaTeXValue, IVariable {
	public double getMolarMass();
	public String getMolecularFormula();
	public String getEmpiricalFormula();
	public String getIUPACName();
	public int getCharge();
}
