package com.ryanafzal.io.calculator.resources.equations.evaluation;

public class Bracket implements Expression {
	
	private BracketType type;
	private Expression inside;
	
	public Bracket(BracketType type, Expression inside) {
		this.type = type;
		this.inside = inside;
	}
	
	public Expression getExpression() {
		return this.inside;
	}
	
	public enum BracketType {
		
		PARENTHESES("(", ")"), 
		CURLYBRACES("{", "}"), 
		SQUAREBRACKETS("[", "]"), 
		ANGLEBRACKETS("<", ">");
		
		private String open;
		private String close;
		
		private BracketType(String open, String close) {
			this.open = open;
			this.close = close;
		}
		
		public String getOpen() {
			return this.open;
		}
		
		public String getClose() {
			return this.close;
		}
	}
	
	public static BracketType getBracketTypeFromBrackets(String open, String close) {
		for (BracketType type : BracketType.values()) {
			if (type.getOpen().equals(open) && type.getClose().equals(close)) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Bracket " + open + " " + close + " does not exist");
	}

}
