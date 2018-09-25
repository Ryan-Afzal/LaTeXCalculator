package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs;

public class StructuralException extends Exception {
	
	private static final long serialVersionUID = -1309153004531638471L;

	public StructuralException() {
		this("Structural Exception");
	}

	public StructuralException(String arg0) {
		super(arg0);
	}

	public StructuralException(Throwable arg0) {
		super(arg0);
	}

	public StructuralException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public StructuralException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
