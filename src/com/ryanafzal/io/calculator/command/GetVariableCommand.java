package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.environment.Experiment;
import com.ryanafzal.io.calculator.main.Constants;

public class GetVariableCommand extends Command {

	public GetVariableCommand(Environment registry) {
		super("getvar", registry);
	}

	@Override
	public String run(String[] args) {
		if (args.length != 1 || !Constants.isValidVariableName(args[0])) {
			return Constants.COMMAND_CARAT + " ERROR: Incompatible Arguments.";
		}
		
		Experiment exp = this.getEnvironment().getCurrentExperiment();
		String variable = args[0];
		
		//Make sure that 'variable' is a variable
		if (!exp.doesVariableExist(variable)) {
			return Constants.COMMAND_CARAT + " " + variable + " is not a variable.";
		}
		
		return Constants.COMMAND_CARAT + " " + variable + " = " + this.getEnvironment().getCurrentExperiment().getVariable(variable).getValue().toString();
	}

	@Override
	public String getDescription() {
		return "Outputs the value of the specified variable";
	}
	
	@Override
	public String getUsage() {
		return super.getUsage() + " <variable>";
	}

}
