package com.ryanafzal.io.calculator.graph;

import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;
import com.ryanafzal.io.calculator.resources.equations.evaluation.builtin.FunctionException;

import javafx.scene.layout.Pane;

public class GraphPane extends Pane {

	private Graph graph;
	
	public GraphPane() {
		super();
		
		this.graph = new Graph();
		this.getChildren().add(this.graph);
	}
	
	public void graph(NumericalFunction function) {
		try {
			this.graph.graph(function);
		} catch (FunctionException e) {
			e.printStackTrace();
		}
	}
	
}
