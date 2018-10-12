package com.ryanafzal.io.calculator.main;

import java.awt.Color;

import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class OutputArea extends ListView<FlowPane> {

	public static final Color BASE_TEXT_COLOR = Color.BLACK;
	
	public OutputArea() {
		super();
		this.setEditable(false);
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
	
	public void addLine(String line, Color color) {
		
	}
	
	public void addLines(Text[] text) {
		addPane(createTextPane(text));
	}
	
	public void addPane(FlowPane pane) {
		this.getChildren().add(pane);
	}
	
	private FlowPane createTextPane(Text[] text) {
		FlowPane pane = new FlowPane();
		pane.getChildren().addAll(text);
		return pane;
	}

}
