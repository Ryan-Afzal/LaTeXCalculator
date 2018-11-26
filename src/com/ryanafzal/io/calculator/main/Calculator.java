package com.ryanafzal.io.calculator.main;

import java.io.File;

import com.ryanafzal.io.calculator.environment.Environment;
import com.ryanafzal.io.calculator.graph.GraphPane;
import com.ryanafzal.io.calculator.resources.equations.evaluation.Function;
import com.ryanafzal.io.calculator.resources.equations.evaluation.FunctionException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Calculator extends Application {
	
	private Stage primaryStage;
	
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
	
	//Variable display
	/*
	 * TreeItem<String> rootItem = new TreeItem<> ("Inbox", rootIcon);
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        TreeView<String> tree = new TreeView<> (rootItem);        
	 */
	
	//Console
	private OutputArea outputArea;
	private TextField inputField;
	
	//Graphing
	private GraphPane graphPane;
	
	/**
	 * Creates a new calculator.
	 */
	public Calculator() {
		super();		
		this.environment = new Environment(this);
	}
	
	/**
	 * Returns the <tt>Environment</tt> of this object.
	 * @return Returns the Environment
	 */
	protected Environment getEnvironment() {
		return this.environment;
	}
	
	/**
	 * Chooses a file from an 'Open File' dialog.
	 * @param name The name of the dialog window.
	 * @return Returns the file chosen.
	 */
	public File getFileFromDialog(String name) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);
		fileChooser.setInitialDirectory(new File(Constants.FILEPATH));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Experiments", "*." + Constants.EXPERIMENT_FILE_EXTENSION),
                new FileChooser.ExtensionFilter("All Files", "*.*")
                );
		
		return fileChooser.showOpenDialog(this.primaryStage);
	}
	
	/**
	 * Chooses a file from a 'Save File' dialog.
	 * @param name The name of the dialog window.
	 * @return Returns the file chosen.
	 */
	public File getFileFromSaveDialog(String name) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);
		fileChooser.setInitialDirectory(new File(Constants.FILEPATH));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Experiments", "*." + Constants.EXPERIMENT_FILE_EXTENSION),
                new FileChooser.ExtensionFilter("All Files", "*.*")
                );
		
		return fileChooser.showSaveDialog(this.primaryStage);
	}
	
	/**
	 * Outputs a message.
	 * @param message The message to output.
	 */
	public void outputMessage(String message) {
		this.outputArea.addLine(message);
	}
	
	/**
	 * Outputs an error message.
	 * @param message The message to output.
	 */
	public void outputErrorMessage(String message) {
		this.outputArea.addLine(message, Color.RED);
	}
	
	/**
	 * Outputs a 'command formatted' message.
	 * @param message The message to output.
	 */
	public void outputCommandMessage(String message) {
		this.outputArea.addLine(Constants.COMMAND_CARAT + " " + message, Color.BLUE);
	}
	
	@Override
	public void start(Stage stage) {
		this.primaryStage = stage;
		primaryStage.setTitle(Constants.TITLE);
		
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
				File file = getFileFromDialog("Open Experiment");
				if (file != null) {
					environment.open(file);
				}
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
		
		this.edit_item_undo = new MenuItem("Undo");
		this.editMenu.getItems().add(this.edit_item_undo);
		
		this.edit_item_redo = new MenuItem("Redo");
		this.editMenu.getItems().add(this.edit_item_redo);
		
		this.menubar.getMenus().add(this.editMenu);
		
		//Finalize Menu Bar
		root.setTop(this.menubar);
		
		//Center
		VBox centerPane = new VBox();
		
		this.outputArea = new OutputArea();
		centerPane.getChildren().add(this.outputArea);
		
		this.inputField = new TextField();
		this.inputField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String text = inputField.getText();
				if (!text.equals("")) {
					outputArea.addLine(text);
					environment.processCommand(text);
					inputField.setText("");
				}
			}
		});
		this.inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					inputField.setText(environment.getPreviousInput());
				}
			}
		});
		centerPane.getChildren().add(this.inputField);
		
		this.graphPane = new GraphPane();
		root.setRight(this.graphPane);
		
		root.setCenter(centerPane);
		this.primaryStage.setScene(new Scene(root, Constants.WIDTH, Constants.HEIGHT, Color.BLACK));
		this.primaryStage.show();
		
		this.testAllInputs();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Used to test inputs. Tests go between the two console outputs. 
	 */
	private void testAllInputs() {
		try {
			System.out.println("======================");
			System.out.println("####TESTING INPUTS####");
			System.out.println("======================");
			
			//TESTS GO HERE
			System.out.println("###TESTING CHEMICALS###");
			testInput("H2O = [H2O]");
			testInput("H2O");//Should output H2O
			testInput("molarmass(H2O)");//Should output molar mass of water
			testInput("H2O = [H2]");
			testInput("molarmass([H2O])");//Should output molar mass of water
			
			System.out.println("###TESTING CHEMICAL EQUATIONS###");
			//TODO
			
			System.out.println("###TESTING VALUES###");
			testInput("x = 10");
			testInput("x");//Should output 10
			testInput("x = 10~kg");
			testInput("x");//Should output 10 kg
			testInput("x = 10~kg~[H2O]~l");
			testInput("x");//Should output 10 kg H2O l
			testInput("water = [H2O]");
			testInput("x = 10~kg~water~l");
			testInput("x");//Should output 10 kg H2O l
			
			System.out.println("###TESTING EQUATIONS###");
			//TODO
			
			System.out.println("###TESTING FUNCTIONS###");
			testInput("g(x) = {2*x}");
			testInput("f(x) = {g(x) * x}");//f(x) = 2x^2
			testInput("log2(x) = {(log(x) / log(2))}");
			
			testInput("g(2)");//Should output 4
			testInput("f(2)");//Should output 8
			testInput("(g(2))");//Should output 4
			testInput("(f(2))");//Should output 8
			testInput("(g((2)))");//Should output 4
			testInput("(f((2)))");//Should output 8
			
			testInput("log2(0.5)");//Should output -1
			testInput("log2(1)");//Should output 0
			testInput("log2(2)");//Should output 1
			
			System.out.println("###TESTING BUILTINS###");
			testInput("log(1)");//Should output 0
			
			System.out.println("###TESTING GRAPHING###");
			testGraphInput("testLinear(x)", "x", Color.BLACK);
			testGraphInput("testQuadratic(x)", "x^2", Color.RED);
			testGraphInput("testRoot(x)", "x^(1/2)", Color.PURPLE);
			testGraphInput("testRational(x)", "(x^2 - x) / (x - 1)", Color.GREEN);
			testGraphInput("testExponential(x)", "2^x", Color.BLUE);
			testGraphInput("testLogarithm(x))", "log(x) / log(2)", Color.YELLOW);
			
			System.out.println("======================");
			System.out.println("###FINISHED TESTING###");
			System.out.println("======================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper method used to input (via the calculator's command line) the specified String.
	 * @param input The input string.
	 */
	private void testInput(String input) {
		System.out.println("TESTING: " + input);
		this.inputField.setText(input);
		this.inputField.fireEvent(new ActionEvent());
	}
	
	private void testGraphInput(String name, String body, Color color) throws FunctionException {
		System.out.println("TESTING: " + name);
		this.graphPane.graph(Function.getFunctionFromDeclaration(name, body), color);
	}
	
}
