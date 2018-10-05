package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.main.Constants;

public class GetVariableCommand extends Command {

	public GetVariableCommand(Environment registry) {
		super("getvar", registry);
	}

	@Override
	public String run(String[] args) {
		if (this.getEnvironment().getCurrentExperiment().doesVariableExist(args[0])) {
			return Constants.COMMAND_CARAT + " " + args[0] + " = " + this.getEnvironment().getCurrentExperiment().getVariable(args[0]).getValue().getValue();
		} else {
			return Constants.COMMAND_CARAT + " Variable " + args[0] + " does not exist.";
		}
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
