package com.ryanafzal.io.calculator.graph;

import com.ryanafzal.io.calculator.resources.equations.functions.FunctionException;
import com.ryanafzal.io.calculator.resources.equations.functions.NumericalFunction;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GraphPane extends Pane {

	private Graph graph;
	
	public GraphPane() {
		super();
		
		this.graph = new Graph();
		this.getChildren().add(this.graph);
	}
	
	/**
	 * Graphs the input function using the specified color.
	 * @param function The function to graph.
	 * @param color The color to use.
	 * @throws FunctionException If the 
	 */
	public void graph(NumericalFunction function, Color color) throws FunctionException {
		try {
			this.graph.graph(function, color);
		} catch (IllegalArgumentException e) {
			throw new FunctionException("Error graphing function " + function.toString(), e);
		}
	}
	
}
