package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;
import java.util.HashSet;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.equations.Variable;

public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1337L;
	
	private HashSet<ChemicalEquation> chemicals;
	private HashSet<ChemicalEquation> equations;
	
	private HashSet<Variable> variables;
	
	public Experiment() {
		this.chemicals = new HashSet<ChemicalEquation>();
		this.equations = new HashSet<ChemicalEquation>();
		
		this.variables = new HashSet<Variable>();
	}
	
	
	
	public static Experiment getBlankExperiment() {
		return new Experiment();
	}

}
