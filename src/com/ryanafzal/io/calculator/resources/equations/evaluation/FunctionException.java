package com.ryanafzal.io.calculator.resources.equations.evaluation;

/**
 * Describes an exception related to {@code Functions}.
 * @author s-afzalr
 *
 */
public class FunctionException extends Exception {
	
	private static final long serialVersionUID = 3341528666788928072L;

	public FunctionException() {
		super();
	}

	public FunctionException(String arg0) {
		super(arg0);
	}

	public FunctionException(Throwable arg0) {
		super(arg0);
	}

	public FunctionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FunctionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
