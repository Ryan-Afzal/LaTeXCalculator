package com.ryanafzal.io.calculator.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Calculator extends Application {
	
	public static final String TITLE = "Advanced Calculator";
	public static final double WIDTH = 300;
	public static final double HEIGHT = 250;
	
	private Processor processor;
	
	private OutputArea outputArea;
	private TextField inputField;
	
	public Calculator() {
		super();
		
		this.processor = new Processor(this);
	}
	
	protected OutputArea getOutputArea() {
		return this.outputArea;
	}
	
	protected TextField getInputField() {
		return this.inputField;
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		
		this.outputArea = new OutputArea();
		this.outputArea.setEditable(false);
		
		this.inputField = new TextField();
		inputField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String text = inputField.getText();
				processor.processCommand(text);
				outputArea.addLine(text);
				inputField.setText("");
			}
		});
		
		StackPane centerPane = new StackPane();
		centerPane.getChildren().add(this.outputArea);
		centerPane.getChildren().add(this.inputField);
		
		BorderPane root = new BorderPane();
		root.setCenter(centerPane);
		
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
