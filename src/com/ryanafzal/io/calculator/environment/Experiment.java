package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;

public class Experiment implements Serializable {
	
	public Experiment() {
		//TODO
	}
	
	public static Experiment getBlankExperiment() {
		return new Experiment();
	}

}
