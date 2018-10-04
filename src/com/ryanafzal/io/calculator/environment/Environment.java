package com.ryanafzal.io.calculator.environment;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ryanafzal.io.calculator.main.Calculator;

public class Environment {

	//TODO Undo
	//TODO Redo
	
	private Experiment currentExperiment;
	private File experimentFile = null;
	private boolean isSaved;
	
	private Calculator calculator;
	
	public Environment(Calculator calculator) {
		this.calculator = calculator;
		makeNewExperiment();
	}
	
	public void makeNewExperiment() {
		if (!isSaved) {
			//TODO Make a dialog box and confirm 'new'.
			return;
		}
		
		this.currentExperiment = Experiment.getBlankExperiment();
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public void open(File file) {
		if (!isSaved) {
			//TODO Make a dialgo box and confirm 'open'.
			return;
		}
		
		//TODO
	}
	
	public void save() {
		if (this.experimentFile == null) {
			//TODO Save As
		}
		
		//TODO
	}
	
	public void saveas(File file) {
		//TODO
	}
	
	public void undo() {
		//TODO
	}
	
	public void redo() {
		//TODO
	}

}
