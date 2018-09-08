package com.ryanafzal.io.calculator.resources.units;

public class InvalidUnitException extends Exception {

	private static final long serialVersionUID = 9112371752839422271L;
	
	public InvalidUnitException() {
		super();
	}
	
	public InvalidUnitException(Exception e) {
		super(e);
	}
	
	public InvalidUnitException(String s) {
		super(s);
	}
	
}
