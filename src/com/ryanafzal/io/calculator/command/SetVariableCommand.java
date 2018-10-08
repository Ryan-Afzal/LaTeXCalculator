package com.ryanafzal.io.calculator.command;

import java.util.Arrays;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.equations.IVariable;

public class SetVariableCommand extends UndoableCommand {

	public SetVariableCommand(Environment environment) {
		super("setvar", environment);
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
		if (exp.isKeyword(variable) || !exp.doesVariableExist(variable)) {
			return Constants.COMMAND_CARAT + " " + variable + " is not a variable.";
		}
		
		String[] values = Arrays.copyOfRange(args, 1, args.length);
		IVariable value = exp.getValueFromKey(values);
		exp.setVariable(variable, value);
		exp.addKeyword(variable);
		this.getEnvironment().setUnsaved();
		return Constants.COMMAND_CARAT + " Set variable " + variable + " to " + value.toString();
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
