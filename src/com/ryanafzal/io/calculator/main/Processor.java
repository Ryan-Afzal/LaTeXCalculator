package com.ryanafzal.io.calculator.main;

public class Processor {
	
	private Calculator calculator;
	
	public Processor(Calculator calculator) {
		this.calculator = calculator;
	}
	
	protected void processCommand(String command) {
		String outputString = "";
		
		this.calculator.getOutputArea().addLine(outputString);
	}
	
}
