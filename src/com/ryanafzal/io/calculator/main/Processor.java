package com.ryanafzal.io.calculator.main;

import com.ryanafzal.io.calculator.environment.Environment;

public class Processor {
	
	private Calculator calculator;
	private Environment environment;
	
	public Processor(Calculator calculator) {
		this.calculator = calculator;
		this.environment = this.calculator.getEnvironment();
	}
	
	protected void processCommand(String command) {
		String outputString = "";
		
		this.calculator.getOutputArea().addLine(outputString);
	}
	
}
