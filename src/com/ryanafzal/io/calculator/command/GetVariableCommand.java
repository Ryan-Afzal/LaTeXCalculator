package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;

public class GetVariableCommand extends Command {

	public GetVariableCommand(Environment registry) {
		super("getvar", registry);
	}

	@Override
	public String run(String[] args) {
		return ">> " + args[0] + " = " + this.getEnvironment().getCurrentExperiment().getVariable(args[0]).getValue().getValue();
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
