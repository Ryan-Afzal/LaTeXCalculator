package com.ryanafzal.io.calculator.environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.ryanafzal.io.calculator.command.Command;
import com.ryanafzal.io.calculator.main.Calculator;
import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AbstractChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IUPACNames;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Environment {

	//TODO Undo
	//TODO Redo
	
	private Experiment currentExperiment;
	private File experimentFile = null;
	private boolean isSaved = true;
	
	private Calculator calculator;
	
	public Environment(Calculator calculator) {
		this.calculator = calculator;
		
		this.makeNewExperiment();
	}
	
	public void makeNewExperiment() {
		if (!isSaved && !confirmOverride("New Experiment")) {
			return;
		}
		
		this.currentExperiment = Experiment.getBlankExperiment();
		this.isSaved = true;
	}
	
	public Experiment getCurrentExperiment() {
		return this.currentExperiment;
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public void open(File file) {
		if (!isSaved && !confirmOverride("Open")) {
			return;
		}
		
		Experiment exp = this.readFromFile(file);
		if (exp != null) {
			this.currentExperiment = exp;
			this.experimentFile = file;
			this.isSaved = true;
		}
	}
	
	private boolean confirmOverride(String type) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm " + type);
		alert.setContentText("The current experiment is unsaved. Do you wish to proceed?");
		
		ButtonType buttonSave = new ButtonType("Save");
		ButtonType buttonNoSave = new ButtonType("Don't Save");
		ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonSave, buttonNoSave, buttonCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonSave) {
			this.save();
			return true;
		} else if (result.get() == buttonNoSave) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	private void writeToFile(File file, Experiment experiment) {		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
			
			output.writeObject(experiment);
			output.flush();
			
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Experiment readFromFile(File file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			Experiment experiment = (Experiment) input.readObject();
			input.close();
			
			return experiment;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void save() {
		if (this.experimentFile == null) {
			this.saveas();
		} else {
			this.writeToFile(this.experimentFile, this.currentExperiment);
		}
		this.isSaved = true;
	}
	
	public void saveas() {
		this.experimentFile = this.calculator.getFileFromSaveDialog("Save As");
		if (this.experimentFile != null) {
			this.save();
		}
	}
	
	public void undo() {
		//TODO
	}
	
	public void redo() {
		//TODO
	}
	
	public void processCommand(String command) {
		
		if (command.contains("=")) {
			int equals_sign_index = command.indexOf("=");
			String name = command.substring(0, equals_sign_index).trim();
			String expression = command.substring(equals_sign_index + 1).trim();
			
			//Ensure syntaxes
			if (!ensureSyntaxes(name, expression)) {
				this.calculator.outputErrorMessage("ERROR: Invalid Input");
				return;
			}
			
			if (expression.contains("->")) {
				//Chemical Equation
			} else if (expression.contains("[")) {
				//Chemical
				this.currentExperiment.setVariable(
						name, 
						this.getChemicalFromKey(
									expression.substring(
												expression.indexOf("[") + 1, 
												expression.indexOf("]"))));
			} else if (expression.contains("{")) {
				//Function
			} else {
				//Value
				try {
					/*
					 * PROBLEM:
					 * Needs a delimeter that is not space, for UnitValue and ChemicalValue.
					 * TODO
					 */
					String[] keys = expression.split(" ");
					double value = this.evaluateExpression(keys[0]);
					
					this.currentExperiment.setVariable(name, this.getValueFromKey(value, Arrays.copyOfRange(keys, 1, keys.length)));
				} catch (Exception e) {
					e.printStackTrace();
					this.calculator.outputErrorMessage("ERROR: " + e.getMessage());
				}
			}
			
		} else {
			try {
				if (this.currentExperiment.doesVariableExist(command)) {
					this.calculator.outputCommandMessage(this.currentExperiment.getVariable(command).toString());
				} else {
					this.calculator.outputCommandMessage(this.evaluateExpression(command) + "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.calculator.outputErrorMessage("ERROR: Invalid Input");
			}
		}
		
	}
	
	private String replaceFunctions(String input) {
		return input;
	}
	
	private boolean ensureSyntaxes(String name, String expression) {
		boolean exp_contains_curlybrace_left = expression.contains("{");
		boolean exp_contains_curlybrace_right = expression.contains("}");
		
		//Braces with no closures/Braces with no parentheses
		if ((exp_contains_curlybrace_left ^ exp_contains_curlybrace_right) 
				|| ((exp_contains_curlybrace_left && exp_contains_curlybrace_right) ^ (name.contains("(") && name.contains(")")))) {
			return false;
		}
		
		//Square Brackets with no closures
		if (expression.contains("[") ^ expression.contains("]")) {
			return false;
		}
		
		//Ensure correct closures [[BASIC]]
		if (expression.indexOf("}") < expression.indexOf("{") || expression.indexOf("]") < expression.indexOf("[")) {
			return false;
		}
		
		//Chemical equation arrow with no chemical brackets
		if (expression.contains("->") && (!expression.contains("["))) {
			return false;
		}
		
		return true;
	}
	
	public double evaluateExpression(String expression) {
		expression = replaceFunctions(expression);
		HashMap<String, Value> values = this.currentExperiment.getValueVariables();
		
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		for (String variable : values.keySet()) {
			variables.set(variable, values.get(variable).getValue());
		}
		
		return new DoubleEvaluator().evaluate(expression, variables);
	}
	
	public void setUnsaved() {
		this.isSaved = false;
	}
	
	public IChemical getChemicalFromKey(String input) {
		if (Constants.isMolecularFormula(input)) {
			return AbstractChemical.getAbstractChemicalFromString(input);
		} else {
			return IUPACNames.getChemicalFromIUPACName(input);
		}
	}
	
	public IVariable getValueFromKey(double value, String[] keys) {
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
				
				int i = keys[0].indexOf(p.getSymbol());
				
				if (i != -1) {
					return new UnitValue(value, Unit.getUnitFromString(keys[0].substring(i + p.getSymbol().length()), p));
				}
			}
			return new UnitValue(value, Unit.getUnitFromString(keys[0]));
		}
		
		return new Value(value);
	}
	
}
