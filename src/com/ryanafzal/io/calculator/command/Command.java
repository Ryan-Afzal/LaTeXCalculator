package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;

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
	
	protected Environment getEnvironment() {
		return this.environment;
	}
	
	public abstract String run(String[] args);
	public abstract String getDescription();
	
}
