package com.ryanafzal.io.calculator.environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.ryanafzal.io.calculator.command.Command;
import com.ryanafzal.io.calculator.main.Calculator;
import com.ryanafzal.io.calculator.main.Constants;
import com.ryanafzal.io.calculator.resources.equations.IVariable;
import com.ryanafzal.io.calculator.resources.equations.Value;

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
	
	private ArrayList<Command> commands;
	
	public Environment(Calculator calculator) {
		this.calculator = calculator;
		
		this.commands = new ArrayList<Command>();
		
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
	
	public Command getCommandAt(int i) {
		return this.commands.get(i);
	}
	
	public Command getCommandFromName(String name) {
		return this.commands.stream().filter(command -> command.getName().equals(name)).findFirst().orElse(null);
	}
	
	public int size() {
		return this.commands.size();
	}
	
	public void processCommand(String command) {
		//String outputString = "";
		
		//String regex_curlybraces = "\\{([^\\{]*)\\}|(\\S+)";
		
		if (command.contains("=")) {
			int equals_sign_index = command.indexOf("=");
			String name = command.substring(0, equals_sign_index).trim();
			String expression = command.substring(equals_sign_index).trim();
			
			//Ensure syntaxes
			if (!ensureSyntaxes(name, expression)) {
				this.calculator.outputErrorMessage("ERROR: Invalid Input");
				return;
			}
			
			
			
		} else {
			try {
				this.calculator.outputCommandMessage(evaluateExpression(command, this.currentExperiment.getValueVariables()));
			} catch (Exception e) {
				e.printStackTrace();
				this.calculator.outputErrorMessage("ERROR: Invalid Input");
			}
		}
		
	}
	
	private boolean ensureSyntaxes(String name, String expression) {
		boolean exp_contains_curlybrace_left = expression.contains("{");
		boolean exp_contains_curlybrace_right = expression.contains("}");
		
		if ((exp_contains_curlybrace_left ^ exp_contains_curlybrace_right) 
				|| ((exp_contains_curlybrace_left && exp_contains_curlybrace_right) ^ (name.contains("(") && name.contains(")")))) {
			return false;
		}
		
		return true;
	}
	
	public static String evaluateExpression(String expression, HashMap<String, Value> map) {
		StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
		for (String variable : map.keySet()) {
			variables.set(variable, ((Value) map.get(variable)).getValue());
		}
		return "" + new DoubleEvaluator().evaluate(expression);
	}
	
	public void setUnsaved() {
		this.isSaved = false;
	}
	
}
