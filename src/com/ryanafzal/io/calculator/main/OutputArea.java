package com.ryanafzal.io.calculator.main;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OutputArea extends ScrollPane {

	public static final Color BASE_TEXT_COLOR = Color.SPRINGGREEN;
	
	private VBox box;
	
	public OutputArea() {
		super();
		this.box = new VBox();
		this.box.setStyle(""
				+ "-fx-background-color: #000000; "
				);
		this.setContent(this.box);
	}
	
	public void addLine(String line) {
		this.addLine(line, BASE_TEXT_COLOR);
	}
	
	public void addLine(String line, Color color) {
		Text text = new Text();
		text.setText(line);
		text.setFont(Font.font("Consolas", 20));
		text.setFill(color);
		
		this.box.getChildren().add(text);
		this.layout();
		this.setVvalue(this.getVmax());
	}
	
}
