package com.ryanafzal.io.calculator.resources.equations.functions.builtin;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.equations.functions.Function;

public class MolarMassFunction extends Function {
	
	private Experiment exp;
	
	public MolarMassFunction(Experiment exp) {
		super(new String[] {"chemical"}, "molarmass(chemical)");
		this.exp = exp;
	}
	
	@Override
	public String evaluate(String[] args) {
		if (this.exp.doesVariableExist(args[0])) {
			return this.exp.getChemicalVariables().get(args[0]).getMolarMass() + "";
		}
		
		args[0].replace("[", "");
		args[0].replace("]", "");
		IChemical chemical = Environment.getChemicalFromKey(args[0]);
		return chemical.getMolarMass() + "";
	}
	
}
