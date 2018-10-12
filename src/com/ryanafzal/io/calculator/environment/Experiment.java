package com.ryanafzal.io.calculator.environment;

import java.io.Serializable;
import java.util.HashMap;

import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AbstractChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IUPACNames;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.units.TemperatureUnit;
import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1337L;
	
	private HashMap<String, IVariable> variables;
	
	public Experiment() {
		this.variables = new HashMap<>();
	}
	
	public void setVariable(String variable, IVariable value) {
		this.variables.put(variable, value);
	}
	
	public void deleteVariable(String variable) {
		this.variables.remove(variable);
	}
	
	/*public void addKeyword(String keyword) {
	 	this.keywords.add(keyword);
	}*/
	
	/*public boolean isKeyword(String keyword) {
		return this.keywords.contains(keyword);
	}*/
	
	/*public void deleteKeyword(String keyword) {
		this.keywords.remove(keyword);
	}*/
	
	public IChemical getChemicalFromKey(String input) {
		if (Constants.isMolecularFormula(input)) {
			return AbstractChemical.getAbstractChemicalFromString(input);
		} else {
			return IUPACNames.getChemicalFromIUPACName(input);
		}
	}
	
	/*public IVariable getValueFromKey(String[] keys) {
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
			return this.variables.get(key);
		}
		
		return new Value(Double.parseDouble(key));
	}*/
	
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
