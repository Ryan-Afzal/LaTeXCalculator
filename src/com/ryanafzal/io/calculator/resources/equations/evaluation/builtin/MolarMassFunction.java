package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;

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
		
		return Environment.getChemicalFromKey(args[0]).getMolarMass() + "";
	}
	
}
