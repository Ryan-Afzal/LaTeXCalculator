package com.ryanafzal.io.calculator.command;

import java.util.ArrayList;

public class CommandRegistry {
	
	private ArrayList<Command> commands;
	
	public CommandRegistry() {
		this.commands = new ArrayList<Command>();
		this.commands.add(new ListCommand(this));
	}
	
	public Command getCommandAt(int i) {
		return this.commands.get(i);
	}
	
	public Command getCommandFromName(String name) {
		return this.commands.stream().filter(command -> command.getAllNames().contains(name)).findFirst().orElse(null);
	}
	
	public int size() {
		return this.commands.size();
	}
	
}
