package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.stoichiometry.Stoichiometry;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;
import com.ryanafzal.io.calculator.resources.units.InvalidUnitException;
import com.ryanafzal.io.calculator.resources.units.Unit;

public class MolarMassFunction extends Function {

	public MolarMassFunction() {
		super(new String[] {"chemical"}, "molarmass(chemical)");
	}
	
	@Override
	public String evaluate(String[] args) {
		args[0].replace("[", "");
		args[0].replace("]", "");
		
		return Environment.getChemicalFromKey(args[0]).getMolarMass() + "";
	}
	
}
