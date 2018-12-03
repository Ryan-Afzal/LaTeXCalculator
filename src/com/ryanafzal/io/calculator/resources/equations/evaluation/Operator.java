package com.ryanafzal.io.calculator.resources.equations.evaluation;

import java.util.Iterator;

public enum Operator implements Expression {

	ADD("+", Associativity.LEFT, 1) {
		public Double evaluate(Iterator<Double> operands) {
			Double o1 = operands.next();
			Double o2 = operands.next();
			
			return new Double(o1.doubleValue() + o2.doubleValue());
		}
	},
	
	MINUS("-", Associativity.LEFT, 1) {
		public Double evaluate(Iterator<Double> operands) {
			Double o1 = operands.next();
			Double o2 = operands.next();
			
			return new Double(o1.doubleValue() - o2.doubleValue());
		}
	},
	
	MULTIPLY("*", Associativity.LEFT, 2) {
		public Double evaluate(Iterator<Double> operands) {
			Double o1 = operands.next();
			Double o2 = operands.next();
			
			return new Double(o1.doubleValue() * o2.doubleValue());
		}
	},
	
	DIVIDE("/", Associativity.LEFT, 2) {
		public Double evaluate(Iterator<Double> operands) {
			Double o1 = operands.next();
			Double o2 = operands.next();
			
			return new Double(o1.doubleValue() / o2.doubleValue());
		}
	},
	
	EXPONATE("^", Associativity.LEFT, 3) {
		public Double evaluate(Iterator<Double> operands) {
			Double o1 = operands.next();
			Double o2 = operands.next();
			
			return new Double(Math.pow(o1.doubleValue(), o2.doubleValue()));
		}
	};
	
	public enum Associativity {
		NONE, LEFT, RIGHT;
	}
	
	private String operator;
	private Associativity associativity;
	private int priority;
	
	private Operator(String operator, Associativity associativity, int priority) {
		this.operator = operator;
		this.associativity = associativity;
		this.priority = priority;
	}
	
	public String getOperator() {
		return this.operator;
	}
	
	public Associativity getAssociativity() {
		return this.associativity;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public abstract Double evaluate(Iterator<Double> operands);
	
	public static Operator getOperator(String operator) {		
		for (Operator o : Operator.values()) {
			if (o.getOperator().equals(o)) {
				return o;
			}
		}
		
		throw new IllegalArgumentException("Operator " + operator + " does not exist");
	}

}
