package com.ryanafzal.io.calculator.main;

import com.ryanafzal.io.calculator.command.Command;
import com.ryanafzal.io.calculator.command.CommandRegistry;
import com.ryanafzal.io.calculator.environment.Environment;

public class Processor {
	
	private Calculator calculator;
	private Environment environment;
	private CommandRegistry registry;
	
	public Processor(Calculator calculator) {
		this.calculator = calculator;
		this.environment = this.calculator.getEnvironment();
		this.registry = new CommandRegistry();
	}
	
	protected void processCommand(String command) {
		String outputString = "";
		
		Command c = this.registry.getCommandFromName(command.substring(1));
		
		if (command.charAt(0) != Constants.COMMAND_OPERATOR || c == null) {
			this.calculator.outputMessage("\"" + command + "\" is not a valid command. Type " + Constants.COMMAND_OPERATOR + "list for a list of commands.");
		} else {
			outputString = c.run(this.environment);
		}
		
		this.calculator.outputMessage(outputString);
	}
	
}
