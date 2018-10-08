package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;

public class DeleteVariableCommand extends UndoableCommand {

	public DeleteVariableCommand(Environment environment) {
		super("deletevar", environment);
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
		if (exp.isKeyword(variable) && !exp.doesVariableExist(variable)) {
			return Constants.COMMAND_CARAT + " " + variable + " is not a variable.";
		}
		
		exp.deleteKeyword(variable);
		exp.deleteVariable(variable);
		
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
