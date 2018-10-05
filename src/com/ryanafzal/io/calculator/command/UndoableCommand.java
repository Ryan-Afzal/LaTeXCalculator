package com.ryanafzal.io.calculator.command;

import java.util.List;

import com.ryanafzal.io.calculator.environment.Environment;

public abstract class UndoableCommand extends Command {

	protected UndoableCommand(List<String> names, CommandRegistry registry) {
		super(names, registry);
	}
	
	public abstract void undo(Environment environment);

}
