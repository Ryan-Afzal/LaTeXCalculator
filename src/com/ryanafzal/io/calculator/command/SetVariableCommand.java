package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;

public final class SetVariableCommand extends UndoableCommand {

	public SetVariableCommand(Environment environment) {
		super("setvar", environment);
	}

	@Override
	public void undo(String[] args) {
		//Unknown
	}

	@Override
	public String run(String[] args) {
		if (args.length < 2) {
			return Constants.COMMAND_CARAT + " ERROR: Incompatible Arguments.";
		}
		
		String variable = args[0];
		String value = args[1];
		Experiment exp = this.getEnvironment().getCurrentExperiment();
		exp.setVariable(variable, exp.getValueFromKey(value));
		return "Set variable " + variable + " to " + value;
	}

	@Override
	public String getDescription() {
		return "Sets the specified variable to the specified value.";
	}
	
	@Override
	public String getUsage() {
		return super.getUsage() + " <variable> <value>";
	}
	
}
