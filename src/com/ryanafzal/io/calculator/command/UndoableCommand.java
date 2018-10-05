package com.ryanafzal.io.calculator.command;

import com.ryanafzal.io.calculator.environment.Environment;

public abstract class UndoableCommand extends Command {

	public UndoableCommand(String name, Environment environment) {
		super(name, environment);
	}
	
	public abstract void undo(String[] args);

}
