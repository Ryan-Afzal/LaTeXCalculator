package com.ryanafzal.io.calculator.main;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OutputArea extends VBox {

	public static final Color BASE_TEXT_COLOR = Color.SPRINGGREEN;
	
	public OutputArea() {
		super();
		this.setStyle(
				"-fx-background-color: #000000; "
				+ "");
		
		
		/*this.setStyle(
				"-fx-control-inner-background:#000000; "
				+ "-fx-font-family: Consolas; "
				+ "-fx-highlight-fill: #00ff00; "
				+ "-fx-highlight-text-fill: #000000; "
				+ "-fx-text-fill: #00ff00;");*/
	}
	
	public void addLine(String line) {
		addLine(line, BASE_TEXT_COLOR);
	}
	
	public void addLine(String line, Color color) {
		Text text = new Text();
		text.setText(line);
		text.setFont(Font.font("Consolas", 20));
		text.setFill(color);
		this.getChildren().add(text);
	}
	
}
