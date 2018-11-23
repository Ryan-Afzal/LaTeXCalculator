package com.ryanafzal.io.calculator.graph;

import java.awt.Point;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;
import com.ryanafzal.io.calculator.resources.equations.evaluation.builtin.FunctionException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Graph extends Canvas {
	
	private static final double HEIGHT = 400;
	private static final double WIDTH = 400;
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	private NumericalFunction currentFunction;
	
	public Graph() {
		super();
		this.setHeight(HEIGHT);
		this.setWidth(WIDTH);
		
		this.xmin = -20;
		this.xmax = 20;
		this.ymin = -20;
		this.ymax = 20;
		
		this.refreshPane();
	}
	
	public void graph(NumericalFunction function) throws FunctionException {
		if (function.numArgs() == 0) {
			GraphicsContext gc = this.getGraphicsContext2D();
			double output = new DoubleEvaluator().evaluate(function.evaluate(new String[] {}));
			
			gc.strokeLine(0, output, this.getWidth(), output);
		} else if (function.numArgs() == 1) {
			for (double i = this.xmin; i <= xmax; i++) {
				try {
					this.plot(i, new DoubleEvaluator().evaluate(function.evaluate(new String[] {"" + i})).doubleValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new FunctionException("Cannot graph a function with more than one parameter.");
		}
	}
	
	private void graph(double x, double y) {
		
	}
	
	private void plot(double x, double y) {
		this.getGraphicsContext2D().strokeOval(this.applyXTransformation(x), this.applyYTransformation(y), 1, 1);
	}
	
	private double applyXTransformation(double input) {
		return input;//return -(input * ((this.getWidth()) / (this.xmax - this.xmin)));
	}
	
	private double applyYTransformation(double input) {
		return -(input * ((this.getHeight()) / (this.ymax - this.ymin)));
	}
	
	private void refreshPane() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		//Wipe pane
		gc.clearRect(0, 0, this.getHeight(), this.getWidth());
		
		//Redraw Axes
		gc.strokeLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);//X-Axis
		gc.strokeLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
	}
	
}
