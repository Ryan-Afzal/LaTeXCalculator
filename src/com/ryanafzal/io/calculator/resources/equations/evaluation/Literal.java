package com.ryanafzal.io.calculator.resources.equations.evaluation;

public class Literal implements Expression {
	
	private Double value;
	
	public Literal(Double value) {
		this.value = value;
	}
	
	public Double getValue() {
		return this.value;
	}

}
