package com.ryanafzal.io.calculator.command;

import java.util.Arrays;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.main.Constants;

public class ListCommand extends Command {

	protected ListCommand(CommandRegistry registry) {
		super(Arrays.asList(new String[] {"list", "help"}), registry);
	}

	@Override
	public String run(Environment environment) {
		return "TESTING";
	}
	
	@Override
	public String getDescription() {
		return "Lists all commands";
	}

}
