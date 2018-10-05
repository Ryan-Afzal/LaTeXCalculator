package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.HashMap;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Chemical;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.equations.Variable;
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
	}
	
	public void setVariable(String variable, IVariable value) {
		if (doesVariableExist(variable)) {
			this.variables.put(variable, new Variable(variable, value));
		} else {
			this.variables.get(variable).setValue(value);
		}
	}
	
	public IVariable getValueFromKey(String key) {
		String[] keys = key.split(" ");
		
		if (keys.length == 4) {
			//Chemical Value
		}
		
		if (keys.length == 3) {
			throw new IllegalArgumentException();
		}
		
		if (keys.length == 2) {
			//Unit value
			return new UnitValue(Double.parseDouble(keys[0]), Unit.getUnitFromString(keys[1].substring(1), Prefix.getPrefixFromString("" + keys[1].charAt(0))));
		}
		
		if (this.doesVariableExist(key)) {
			return this.variables.get(key).getValue();
		}
		
		return new Value(Double.parseDouble(key));
	}
	
	public boolean doesVariableExist(String variable) {
		return this.variables.containsKey(variable);
	}
	
	public static Experiment getBlankExperiment() {
		return new Experiment();
	}

}
