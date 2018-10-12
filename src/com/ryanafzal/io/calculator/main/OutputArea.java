package com.ryanafzal.io.calculator.main;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OutputArea extends VBox {

	public static final String BASE_TEXT_COLOR = "green";
	
	public OutputArea() {
		super();
		this.setStyle(
				"-fx-control-inner-background:#000000; "
				+ "-fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #00ff00; "
				+ "-fx-highlight-text-fill: #000000; "
				+ "-fx-text-fill: #00ff00;");
	}
	
	public void addLine(String line) {
		addLine(line, BASE_TEXT_COLOR);
	}
	
	public void addLine(String line, String color) {
		Text text = new Text();
		text.setText(line);
		text.setStyle("-fx-control-text-fill: " + color + "; -fx-font-family: Consolas; -fx-font-size: 20");
		this.getChildren().add(text);
	}
	
}
