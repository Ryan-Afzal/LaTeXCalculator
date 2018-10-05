package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.main.Constants;

public final class ListCommand extends Command {

	public ListCommand(Environment environment) {
		super("list", environment);
	}

	@Override
	public String run(String[] args) {
		String output = (Constants.COMMAND_CARAT + " List of Commands:\n");
		
		for (int i = 0; i < this.getEnvironment().size(); i++) {
			Command command = this.getEnvironment().getCommandAt(i);
			output += ("\t" + Constants.COMMAND_OPERATOR + command.getName() + " : " + command.getDescription() + "\n");
		}
		
		return output;
	}
	
	@Override
	public String getDescription() {
		return "Lists all commands";
	}

}
