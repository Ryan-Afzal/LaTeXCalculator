package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AbstractChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;
import com.ryanafzal.io.calculator.resources.equations.evaluation.builtin.MolarMassFunction;
import com.ryanafzal.io.calculator.resources.equations.evaluation.builtin.SecantFunction;
import com.ryanafzal.io.calculator.resources.equations.evaluation.builtin.StoichiometryFunction;
import com.ryanafzal.io.calculator.resources.units.TemperatureUnit;

public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1337L;
	
	private HashMap<String, IVariable> variables;
	private HashSet<String> keywords;
	
	public Experiment() {
		this.variables = new HashMap<String, IVariable>();
		this.keywords = new HashSet<String>();
		//Mathematical Keywords
		this.keywords.add("sin");
		this.keywords.add("cos");
		this.keywords.add("csc");
		this.keywords.add("sec");
		this.keywords.add("cot");
		this.keywords.add("log");
		this.keywords.add("lim");
		this.keywords.add("ln");
		this.keywords.add("int");
		this.keywords.add("!");
		
		//Builtin Functions
		this.keywords.add("stoichiometry");
		this.keywords.add("molarmass");
		
		//Mathematical Keywords
		this.variables.put("sec", new SecantFunction());
		
		//Builtin Functions
		this.variables.put("stoichiometry", new StoichiometryFunction(this));
		this.variables.put("molarmass", new MolarMassFunction());
		
	}
	
	public void setVariable(String variable, IVariable value) {
		if (this.isKeyword(variable)) {
			throw new IllegalArgumentException("Cannot redefine a keyword");
		}
		
		this.variables.put(variable, value);
	}
	
	public void deleteVariable(String variable) {
		this.variables.remove(variable);
	}
	
	public Set<String> getKeySet() {
		return this.variables.keySet();
	}
	
	public HashMap<String, Value> getValueVariables() {
		HashMap<String, Value> output = new HashMap<String, Value>();
		this.variables.keySet()
			.stream()
			.filter(key -> this.variables.get(key) instanceof Value)
			.collect(Collectors.toSet())
			.forEach(key -> output.put(key, (Value) this.variables.get(key)));
		
		return output;
	}
	
	public HashMap<String, IChemical> getChemicalVariables() {
		HashMap<String, IChemical> output = new HashMap<String, IChemical>();
		this.variables.keySet()
			.stream()
			.filter(key -> this.variables.get(key) instanceof IChemical)
			.collect(Collectors.toSet())
			.forEach(key -> output.put(key, (IChemical) this.variables.get(key)));
		
		return output;
	}
	
	public HashMap<String, Function> getFunctionVariables() {
		HashMap<String, Function> output = new HashMap<String, Function>();
		this.variables.keySet()
			.stream()
			.filter(key -> this.variables.get(key) instanceof Function)
			.collect(Collectors.toSet())
			.forEach(key -> output.put(key, (Function) this.variables.get(key)));
		
		return output;
	}
	
	public HashMap<String, ChemicalEquation> getChemicalEquationVariables() {
		HashMap<String, ChemicalEquation> output = new HashMap<String, ChemicalEquation>();
		this.variables.keySet()
			.stream()
			.filter(key -> this.variables.get(key) instanceof ChemicalEquation)
			.collect(Collectors.toSet())
			.forEach(key -> output.put(key, (ChemicalEquation) this.variables.get(key)));
		
		return output;
	}
	
	public boolean isKeyword(String keyword) {
		return this.keywords.contains(keyword);
	}
	
	public boolean doesVariableExist(String variable) {
		return this.variables.containsKey(variable);
	}
	
	public IVariable getVariable(String key) {
		return this.variables.get(key);
	}
	
	public static Experiment getBlankExperiment() {
		return new Experiment();
	}

}
