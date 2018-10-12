package com.ryanafzal.io.calculator.environment;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.ryanafzal.io.calculator.command.Command;
import com.ryanafzal.io.calculator.main.Calculator;
import com.ryanafzal.io.calculator.main.Constants;

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
		String outputString = "";
		
		String regex = "\\{([^\\{]*)\\}|(\\S+)";
		
		String[] args = Constants.splitString(command, regex);
		
		System.out.println(Arrays.toString(args));
		Command c = this.getCommandFromName(args[0].substring(1));
		
		if (args[0].charAt(0) != Constants.COMMAND_OPERATOR || c == null) {
			this.calculator.outputMessage("\"" + command + "\" is not a valid command. Type " + Constants.COMMAND_OPERATOR + "list for a list of commands.");
		} else {
			outputString = c.run(Arrays.copyOfRange(args, 1, args.length));
		}
		
		if (!outputString.equals("")) {
			this.calculator.outputMessage(outputString);
		}
	}
	
	public void setUnsaved() {
		this.isSaved = false;
	}
	
}
