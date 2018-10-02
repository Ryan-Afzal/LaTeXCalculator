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
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		
		TextField inputField = new TextField();
		inputField.setPromptText("command");
		inputField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				processor.processCommand(inputField.getText());
				
				inputField.setText("");
			}
		});
		
		BorderPane root = new BorderPane();
		root.getChildren().add(inputField);
		
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
