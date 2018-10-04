package com.ryanafzal.io.calculator.main;

import java.io.File;

import com.ryanafzal.io.calculator.environment.Environment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Calculator extends Application {
	
	public static final String TITLE = "Advanced Calculator";
	public static final double WIDTH = 1000;
	public static final double HEIGHT = 750;
	
	public static final String FILEPATH = "C:/Users/s-afzalr/Documents/LaTeXCalculator/";
	public static final String EXPERIMENT_FILE_EXTENSION = "exp";
	
	private Stage primaryStage;
	
	private Processor processor;
	private Environment environment;
	
	//Toolbar
	private MenuBar menubar;
	
	private Menu fileMenu;
	private MenuItem file_item_new;
	private MenuItem file_item_open;
	private MenuItem file_item_save;
	private MenuItem file_item_saveas;
	
	private Menu editMenu;
	private MenuItem edit_item_undo;
	private MenuItem edit_item_redo;
	
	//UI Controls
	private OutputArea outputArea;
	private TextField inputField;
	
	public Calculator() {
		super();
		
		this.processor = new Processor(this);
		this.environment = new Environment(this);
	}
	
	protected Environment getEnvironment() {
		return this.environment;
	}
	
	public File getFileFromDialog(String name) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);
		fileChooser.setInitialDirectory(new File(FILEPATH));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Experiments", "*." + EXPERIMENT_FILE_EXTENSION),
                new FileChooser.ExtensionFilter("All Files", "*.*")
                );
		
		return fileChooser.showOpenDialog(this.primaryStage);
	}
	
	public File getFileFromSaveDialog(String name) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);
		fileChooser.setInitialDirectory(new File(FILEPATH));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Experiments", "*." + EXPERIMENT_FILE_EXTENSION),
                new FileChooser.ExtensionFilter("All Files", "*.*")
                );
		
		return fileChooser.showSaveDialog(this.primaryStage);
	}
	
	protected OutputArea getOutputArea() {
		return this.outputArea;
	}
	
	protected TextField getInputField() {
		return this.inputField;
	}
	
	public void outputMessage(String message) {
		this.outputArea.addLine(message);
	}
	
	@Override
	public void start(Stage stage) {
		this.primaryStage = stage;
		primaryStage.setTitle(TITLE);
		
		BorderPane root = new BorderPane();
		
		//Menu Bar
		this.menubar = new MenuBar();
		
		//File Menu
		this.fileMenu = new Menu("File");
		
		this.file_item_new = new MenuItem("New");
		this.file_item_new.setOnAction((ActionEvent e) -> this.environment.makeNewExperiment());
		this.fileMenu.getItems().add(this.file_item_new);
		
		this.file_item_open = new MenuItem("Open");
		this.file_item_open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				environment.open(getFileFromDialog("Open Experiment"));
			}
		});
		this.fileMenu.getItems().add(this.file_item_open);
		
		this.fileMenu.getItems().add(new SeparatorMenuItem());
		
		this.file_item_save = new MenuItem("Save");
		this.file_item_save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				environment.save();
			}
		});
		this.fileMenu.getItems().add(this.file_item_save);
		
		this.file_item_saveas = new MenuItem("Save As");
		this.file_item_saveas.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
                environment.saveas();
			}
		});
		this.fileMenu.getItems().add(this.file_item_saveas);
		
		this.menubar.getMenus().add(this.fileMenu);
		
		//Edit Menu
		this.editMenu = new Menu();
		this.editMenu.setText("Edit");
		
		
		
		this.menubar.getMenus().add(this.editMenu);
		
		//Finalize Menu Bar
		root.setTop(this.menubar);
		
		//Center
		VBox centerPane = new VBox();
		
		this.outputArea = new OutputArea();
		centerPane.getChildren().add(this.outputArea);
		
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
		centerPane.getChildren().add(this.inputField);
		
		
		
		root.setCenter(centerPane);
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
