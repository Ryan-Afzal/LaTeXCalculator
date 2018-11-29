package com.ryanafzal.io.calculator.graph;

import java.math.BigDecimal;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.ryanafzal.io.calculator.resources.equations.evaluation.ExtendedDoubleEvaluator;
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
		if (function.numArgs() == 0) {
			GraphicsContext gc = this.getGraphicsContext2D();
			double output = new DoubleEvaluator().evaluate(function.evaluate(new String[] {}));
			
			gc.strokeLine(0, output, this.getWidth(), output);
		} else if (function.numArgs() == 1) {
			double[][] values = new double[this.getNumValues()][2];
			
			DoubleEvaluator eval = new ExtendedDoubleEvaluator();
			String[] args = {""};
			
			BigDecimal x = BigDecimal.valueOf(this.xmin);
			
			for (int index = 0; index < this.getNumValues(); index++) {
				try {
					System.out.println("INDEX: " + index + ", X: " + x);
					args[0] = x + "";
					values[index][0] = x.doubleValue();
					values[index][1] = eval.evaluate(function.evaluate(args));
				} catch (IllegalArgumentException e) {
					if (!e.getMessage().equals("Invalid argument passed to log")) {
						throw e;
					}
				} finally {
					x.add(BigDecimal.valueOf(this.getPrecisionIncrement()));
				}
			}
			
			
			
			for (int i = 0; i < values.length - 1; i++) {
				this.graph(values[i][0], values[i][1], values[i + 1][0], values[i + 1][1], color);
			}
		} else {
			throw new IllegalArgumentException("Cannot graph a function with more than one parameter.");
		}
	}
	
	private void graph(double x1, double y1, double x2, double y2, Color color) {
		//System.out.println("PRE(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")");
		x1 = this.applyXToPixelsTransformation(x1);
		y1 = this.applyYToPixelsTransformation(y1);
		x2 = this.applyXToPixelsTransformation(x2);
		y2 = this.applyYToPixelsTransformation(y2);
		
		//System.out.println("POST(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + ")");
		
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(color);
		gc.strokeLine(x1, y1, x2, y2);
		
	}
	
	/**
	 * Plots a point at the specified {@code x} and {@code y} values, in the specified {@code Color}.
	 * @param x The x-value.
	 * @param y The y-value.
	 * @param color The color to draw the point in.
	 */
	private void plot(double x, double y, Color color) {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(color);
		gc.strokeOval(this.applyXToPixelsTransformation(x), this.applyYToPixelsTransformation(y), 3, 3);
	}
	
	/**
	 * Applies the transformation to the x-output of an equation. The function turns a regular coordinate into a pixel value. Used when plotting coordinates.
	 * @param input The coordinate x-value
	 * @return The x-value in pixels
	 */
	private double applyXToPixelsTransformation(double input) {
		return ((input - this.xmin) * ((this.getWidth()) / (this.xmax - this.xmin)));
	}
	
	/**
	 * Applies the transformation to the y-output of an equation. The function turns a regular coordinate into a pixel value. Used when plotting coordinates.
	 * @param input The coordinate y-value
	 * @return The y-value in pixels
	 */
	private double applyYToPixelsTransformation(double input) {
		return ((input - this.ymin) * ((this.getHeight()) / (this.ymax - this.ymin)));
	}
	
	/**
	 * Clears the graph pane and redraws the axes.
	 */
	private void clearPane() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		//Wipe pane
		gc.clearRect(0, 0, this.getHeight(), this.getWidth());
		
		//Redraw Axes
		gc.strokeLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);//X-Axis
		gc.strokeLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
		
		//Draw stroke lines
	}
	
	/**
	 * Redraws the pane, clearing it and redrawing all functions and points.
	 */
	private void refreshPane() {
		this.clearPane();
	}
	
}
