package com.ryanafzal.io.calculator.main;

import javafx.scene.control.TextArea;

public class OutputArea extends TextArea {

	public OutputArea() {
		super();
		this.setEditable(false);
	}
	
	protected void addLine(String line) {
		this.setText(this.getText() + "\n" + line);
	}

}
