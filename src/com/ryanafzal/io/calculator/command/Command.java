package com.ryanafzal.io.calculator.command;

import java.util.List;

import com.ryanafzal.io.calculator.environment.Environment;

public abstract class Command {

	private List<String> names;
	private CommandRegistry registry;
	
	protected Command(List<String> names, CommandRegistry registry) {
		this.names = names;
		this.registry = registry;
	}
	
	public List<String> getAllNames() {
		return this.names;
	}
	
	protected CommandRegistry getRegistry() {
		return this.registry;
	}
	
	public abstract String run(Environment environment);
	public abstract String getDescription();
	
}
