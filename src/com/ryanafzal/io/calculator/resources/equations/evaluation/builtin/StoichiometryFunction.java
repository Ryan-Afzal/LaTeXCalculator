package com.ryanafzal.io.calculator.resources.equations.evaluation.builtin;

import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.stoichiometry.Stoichiometry;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.EquationException;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;
import com.ryanafzal.io.calculator.resources.units.InvalidUnitException;
import com.ryanafzal.io.calculator.resources.units.Unit;

public class StoichiometryFunction extends Function {

	private Experiment experiment;
	
	public StoichiometryFunction(Experiment experiment) {
		super(new String[] {"equation", "startingValue", "endingChemical", "unit"}, "stoichiometry(equation,startingValue,endingChemical,unit)");
		this.experiment = experiment;
	}
	
	@Override
	public String evaluate(String[] args) {
		ChemicalEquation equation = this.experiment.getChemicalEquationVariables().get(args[0]);
		
		Stoichiometry stoichiometry = new Stoichiometry(equation);
		
		try {
			return "" + stoichiometry.solveFor(
					(ChemicalValue) this.experiment.getValueVariables().get(args[1]), 
					this.experiment.getChemicalVariables().get(args[2]), 
					Unit.getUnitFromString(args[3]));
		} catch (EquationException e) {
			e.printStackTrace();
		} catch (InvalidUnitException e) {
			e.printStackTrace();
		}
		
		return "ERROR";
	}
	
}
