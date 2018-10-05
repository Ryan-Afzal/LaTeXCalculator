package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.main.Constants;

public abstract class Command {

	private String name;
	private Environment environment;
	
	public Command(String name, Environment registry) {
		this.name = name;
		this.environment = registry;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsage() {
		return Constants.COMMAND_OPERATOR + this.getName();
	}
	
	protected Environment getEnvironment() {
		return this.environment;
	}
	
	public abstract String run(String[] args);
	public abstract String getDescription();
	
}
