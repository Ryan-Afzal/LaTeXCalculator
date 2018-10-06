package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Chemical;
import com.ryanafzal.io.calculator.resources.equations.ExperimentVariable;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.equations.Variable;
import com.ryanafzal.io.calculator.resources.units.TemperatureUnit;
import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1337L;
	
	private HashSet<Chemical> chemicals;
	private HashSet<ChemicalEquation> equations;
	
	private HashMap<String, Variable> variables;
	
	public Experiment() {
		this.chemicals = new HashSet<Chemical>();
		this.equations = new HashSet<ChemicalEquation>();
		
		this.variables = new HashMap<String, Variable>();
		this.variables.put(
				"T", 
				new ExperimentVariable(
						"T", 
						new UnitValue(
								25, 
								new TemperatureUnit()
								)
						)
				);
		//P variable
	}
	
	public void setVariable(String variable, IVariable value) {
		if (!doesVariableExist(variable)) {
			this.variables.put(variable, new Variable(variable, value));
		} else {
			this.variables.get(variable).setValue(value);
		}
	}
	
	public IVariable getValueFromKey(String[] keys) {
		String key = keys[0];
		if (keys.length == 4) {
			//Chemical Value
		}
		
		if (keys.length == 3) {
			throw new IllegalArgumentException();
		}
		
		if (keys.length == 2) {
			//Unit value
			for (Prefix p : Prefix.values()) {
				if (p == Prefix.NONE) {
					continue;
				}
				
				int i = keys[1].indexOf(p.getSymbol());
				
				if (i != -1) {
					return new UnitValue(Double.parseDouble(keys[0]), Unit.getUnitFromString(keys[1].substring(i + p.getSymbol().length()), p));
				}
			}
			return new UnitValue(Double.parseDouble(keys[0]), Unit.getUnitFromString(keys[1]));
		}
		
		if (this.doesVariableExist(key)) {
			return this.variables.get(key).getValue();
		}
		
		return new Value(Double.parseDouble(key));
	}
	
	public boolean doesVariableExist(String variable) {
		return this.variables.containsKey(variable);
	}
	
	public Variable getVariable(String key) {
		return this.variables.get(key);
	}
	
	public static Experiment getBlankExperiment() {
		return new Experiment();
	}

}
