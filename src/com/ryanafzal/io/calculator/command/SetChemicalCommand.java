package com.ryanafzal.io.calculator.command;

import java.util.Arrays;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;

public final class SetChemicalCommand extends UndoableCommand {

	public SetChemicalCommand(Environment environment) {
		super("setchem", environment);
	}

	@Override
	public void undo(String[] args) {
		//Unknown
	}

	@Override
	public String run(String[] args) {
		if (args.length < 2 || args.length > 4 || !Constants.isValidVariableName(args[0])) {
			return Constants.COMMAND_CARAT + " ERROR: Incompatible Arguments.";
		}
		
		Experiment exp = this.getEnvironment().getCurrentExperiment();
		String variable = args[0];
		
		//Make sure that 'variable' is a variable
		if (exp.isKeyword(variable) && !exp.doesVariableExist(variable)) {
			return Constants.COMMAND_CARAT + " " + variable + " is not a variable.";
		}
		
		//Make sure that the 'chemical' is a chemical
		if (!Constants.isValidChemicalInput(args[1])) {
			return Constants.COMMAND_CARAT + " " + args[1] + " is not a valid chemical.";
		}
		
		IChemical value = exp.getChemicalFromKey(args[1].substring(1, args[1].length() - 1));
		exp.setChemical(variable, value);
		exp.addKeyword(variable);
		this.getEnvironment().setUnsaved();
		return Constants.COMMAND_CARAT + " Set chemical " + variable + " to " + value.toString();
	}

	@Override
	public String getDescription() {
		return "Sets the specified chemical to the specified value.";
	}
	
	@Override
	public String getUsage() {
		return super.getUsage() + " <chemical> <value>";
	}
	
}
