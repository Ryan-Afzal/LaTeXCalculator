package com.ryanafzal.io.calculator.graph;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.ryanafzal.io.calculator.resources.equations.evaluation.NumericalFunction;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Graph extends Canvas {
	
	private static final double HEIGHT = 750;
	private static final double WIDTH = 750;
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	private int precision;
	
	public Graph() {
		super();
		this.setHeight(HEIGHT);
		this.setWidth(WIDTH);
		
		this.xmin = -10;
		this.xmax = 10;
		this.ymin = -10;
		this.ymax = 10;
		
		this.precision = -1;
		
		this.refreshPane();
	}
	
	private double getPrecisionIncrement() {
		return Math.pow(10, this.precision);
	}
	
	public int getNumValues() {
		return (int) ((this.xmax - this.xmin) * (1 / this.getPrecisionIncrement())) + 1;
	}
	
	public void graph(NumericalFunction function, Color color) {
		/*if (function.numArgs() == 0) {
			GraphicsContext gc = this.getGraphicsContext2D();
			double output = new DoubleEvaluator().evaluate(function.evaluate(new String[] {}));
			
			gc.strokeLine(0, output, this.getWidth(), output);
		} else if (function.numArgs() == 1) {
			double[][] values = new double[this.getNumValues()][2];
			
			System.out.println("Values List Size: " + values.length);
			DoubleEvaluator eval = new DoubleEvaluator();
			String[] args = {""};
			
			double x = this.xmin;
			
			for (int index = 0; index < this.getNumValues(); index++) {
				try {
					System.out.println("INDEX: " + index + ", X: " + x);
					args[0] = x + "";
					values[index][0] = x;
					values[index][1] = eval.evaluate(function.evaluate(args));
				} catch (IllegalArgumentException e) {
					if (!e.getMessage().equals("Invalid argument passed to log")) {
						throw e;
					}
				} finally {
					x += this.getPrecisionIncrement();
				}
			}
			
			
			
			for (int i = 0; i < values.length - 1; i++) {
				this.graph(values[i][0], values[i][1], values[i + 1][0], values[i + 1][1], color);
			}
		} else {
			throw new IllegalArgumentException("Cannot graph a function with more than one parameter.");
		}*/
	}
	
	private void graph(double x1, double y1, double x2, double y2, Color color) {
		System.out.println("PRE(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")");
		x1 = this.applyXTransformation(x1);
		y1 = this.applyYTransformation(y1);
		x2 = this.applyXTransformation(x2);
		y2 = this.applyYTransformation(y2);
		
		System.out.println("POST(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")");
		
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
		
	}
	
	private void plot(double x, double y, Color color) {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(color);
		gc.strokeOval(this.applyXTransformation(x), this.applyYTransformation(y), 3, 3);
	}
	
	private double applyXTransformation(double input) {
		return ((input - this.xmin) * ((this.getWidth()) / (this.xmax - this.xmin)));
	}
	
	private double applyYTransformation(double input) {
		return ((input - this.ymin) * ((this.getHeight()) / (this.ymax - this.ymin)));
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
