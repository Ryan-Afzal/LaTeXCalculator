package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;

public class DeleteChemicalCommand extends UndoableCommand {

	public DeleteChemicalCommand(Environment environment) {
		super("deletechem", environment);
	}

	@Override
	public void undo(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public String run(String[] args) {
		if (args.length != 1 || !Constants.isValidVariableName(args[0])) {
			return Constants.COMMAND_CARAT + " ERROR: Incompatible Arguments.";
		}
		
		Experiment exp = this.getEnvironment().getCurrentExperiment();
		String variable = args[0];
		
		//Make sure that 'variable' is a variable
		if (!exp.doesChemicalExist(variable)) {
			return Constants.COMMAND_CARAT + " " + variable + " is not a chemical variable.";
		}
		
		exp.deleteKeyword(variable);
		exp.deleteChemical(variable);
		
		return Constants.COMMAND_CARAT + " Deleted variable " + variable;
	}

	@Override
	public String getDescription() {
		return "Deletes the specified variable";
	}
	
	@Override
	public String getUsage() {
		return super.getUsage() + " <variable>";
	}

}
