package com.ryanafzal.io.calculator.main;

import javafx.scene.control.TextArea;

public class OutputArea extends TextArea {

	public OutputArea() {
		super();
		this.setEditable(false);
		this.setStyle(
				"-fx-control-inner-background:#000000; "
				+ "-fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #00ff00; "
				+ "-fx-highlight-text-fill: #000000; "
				+ "-fx-text-fill: #00ff00;");
		this.setPrefRowCount(20);
		this.setPrefColumnCount(100);
	}
	
	protected void addLine(String line) {
		this.setText(this.getText() + "\n" + line);
		this.positionCaret(this.getLength());
	}

}
