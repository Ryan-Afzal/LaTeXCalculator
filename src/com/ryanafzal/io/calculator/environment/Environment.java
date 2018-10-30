package com.ryanafzal.io.calculator.environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.ryanafzal.io.calculator.main.Calculator;
import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AbstractChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IUPACNames;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.Value;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;
import com.ryanafzal.io.calculator.resources.units.Unit;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Environment {

	//TODO Undo
	//TODO Redo
	
	private LinkedList<String> previousInput;
	private int previousIndex;
	
	private Experiment currentExperiment;
	private File experimentFile = null;
	private boolean isSaved = true;
	
	private Calculator calculator;
	
	public Environment(Calculator calculator) {
		this.calculator = calculator;
		this.previousInput = new LinkedList<String>();
		this.previousIndex = 0;
		
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
		this.previousInput.push(command);
		this.previousIndex = 0;
		
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
			} else if (expression.charAt(0) == '[') {
				//Chemical
				this.currentExperiment.setVariable(
						name, 
						getChemicalFromKey(
									expression.substring(
												expression.indexOf("[") + 1, 
												expression.indexOf("]"))));
			} else if (expression.contains("{")) {
				if (expression.contains("=")) {
					//Equation
				} else {
					//Function
					this.currentExperiment
					.setVariable(
							name.substring(
									0, 
									name.indexOf("(")), 
							Function.getFunctionFromDeclaration(
									name, 
									expression
									.substring(
											expression.indexOf("{") + 1, 
											expression.indexOf("}"))));
				}
			} else {
				//Value
				try {
					String[] keys = expression.split("~");
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
				this.calculator.outputErrorMessage("ERROR: " + e.getMessage());
			}
		}
		
	}
	
	public String getPreviousInput() {
		this.previousIndex++;
		return this.previousInput.get(this.previousIndex - 1);
	}
	
	public static String replaceFunctions(String input, HashMap<String, Function> function_map) {
		if (!input.contains("(")) {
			return input;
		}
		
		for (String function_name : function_map.keySet()) {
			//While this function is contained:
			while (input.contains(function_name)) {
				int indexOfFunction = input.indexOf(function_name);//Index of the first function call
				
				String rest = input.substring(indexOfFunction);
				String parentheses_block = getParentheses(rest);
				String[] args = parentheses_block.substring(1, parentheses_block.length() - 1).split(",");
				String whole_function = rest.substring(0, rest.indexOf("(")) + parentheses_block;
				for (int i = 0; i < args.length; i++) {
						args[i] = replaceFunctions(args[i], function_map);
				}
				
				input = 
						input.substring(0, indexOfFunction)
						+ "("
						+ replaceFunctions(function_map.get(function_name).evaluate(args), function_map)
						+ ")"
						+ input.substring(indexOfFunction + whole_function.length());
			}
		}
		
		return input;
	}
	
	/**
	 * Gets the outermost parentheses block.
	 * @param input
	 * @return
	 */
	private static String getParentheses(String input) {
		if (!input.contains("(") || input.contains("(") ^ input.contains(")")) {
			throw new IllegalArgumentException("Does not contain parentheses");
		}
		
		int depth = 1;
		int start = input.indexOf("(");
		for (int i = start + 1; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				depth++;
			} else if (input.charAt(i) == ')') {
				depth--;
				
				if (depth == 0) {
					return input.substring(start, i + 1);
				}
			}
		}
		
		throw new IllegalArgumentException("Mismatched Parentheses");
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
		expression = replaceFunctions(expression, this.currentExperiment.getFunctionVariables());
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
	
	public static IChemical getChemicalFromKey(String input) {
		input = input.replace("[", "");
		input = input.replace("]", "");
		
		if (Constants.isMolecularFormula(input)) {
			return AbstractChemical.getAbstractChemicalFromString(input);
		} else {
			return IUPACNames.getChemicalFromIUPACName(input);
		}
	}
	
	public IVariable getValueFromKey(double value, String[] keys) {
		for (int i = 0; i < keys.length; i++) {
			if (this.currentExperiment.doesVariableExist(keys[i])) {
				keys[i] = this.currentExperiment.getVariable(keys[i]).toString();
			}
		}
		
		if (keys.length == 3) {
			return new ChemicalValue(value, this.getUnitFromKey(keys[0]), getChemicalFromKey(keys[1]), ChemicalState.getStateFromString(keys[2]));
		}
		
		if (keys.length == 2) {
			throw new IllegalArgumentException();
		}
		
		if (keys.length == 1) {
			return new UnitValue(value, this.getUnitFromKey(keys[0]));
		}
		
		return new Value(value);
	}
	
	public Unit getUnitFromKey(String key) {
		for (Prefix p : Prefix.values()) {
			if (p == Prefix.NONE) {
				continue;
			}
			
			int i = key.indexOf(p.getSymbol());
			
			if (i != -1) {
				return Unit.getUnitFromString(key.substring(i + p.getSymbol().length()), p);
			}
		}
		return Unit.getUnitFromString(key);
	}
	
}
