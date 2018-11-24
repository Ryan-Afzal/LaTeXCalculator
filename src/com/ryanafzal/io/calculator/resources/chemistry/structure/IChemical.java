package com.ryanafzal.io.calculator.resources.chemistry.structure;

import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.misc.ILaTeXValue;

public interface IChemical extends ILaTeXValue, IVariable {
	public double getMolarMass();
	public String getMolecularFormula();
	public String getEmpiricalFormula();
	public String getIUPACName();
	public int getCharge();
}
