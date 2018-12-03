package com.ryanafzal.io.calculator.resources.equations.evaluation;

import java.math.BigDecimal;
import java.util.Iterator;

import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

public class BigDecimalEvaluator extends AbstractEvaluator<BigDecimal> {
	
	/** The addition operator */
	public final static Operator ADD = new Operator("+", 2, Operator.Associativity.LEFT, 1);
	/** The subtraction operator */
	public final static Operator SUBTRACT = new Operator("-", 2, Operator.Associativity.LEFT, 1);
	/** The multiplication operator */
	public final static Operator MULTIPLY = new Operator("*", 2, Operator.Associativity.LEFT, 2);
	/** The division operator */
	public final static Operator DIVIDE = new Operator("/", 2, Operator.Associativity.LEFT, 2);
	/** The exponation operator */
	public final static Operator EXPONATE = new Operator("^", 2, Operator.Associativity.LEFT, 3);
	
	/** The evaluator's parameters.*/
	private static final Parameters PARAMETERS;
	static {
		PARAMETERS = new Parameters();
		PARAMETERS.add(ADD);
		PARAMETERS.add(SUBTRACT);
		PARAMETERS.add(MULTIPLY);
		PARAMETERS.add(DIVIDE);
		PARAMETERS.add(EXPONATE);
		
		PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
	}
	
	public BigDecimalEvaluator() {
		super(PARAMETERS);
	}
	   
	@Override
	protected BigDecimal toValue(String literal, Object evaluationContext) {
		return new BigDecimal(literal);
	}
	
	@Override
	protected BigDecimal evaluate (Operator operator, Iterator<BigDecimal> operands, Object evaluationContext ) {
	  if (operator == ADD) {
	    BigDecimal o1 = operands.next();
	    BigDecimal o2 = operands.next();
	    return o1.add(o2);
	  } else if (operator == SUBTRACT) {
		BigDecimal o1 = operands.next();
		BigDecimal o2 = operands.next();
		return o1.subtract(o2);
	  } else if (operator == MULTIPLY) {
		BigDecimal o1 = operands.next();
	    BigDecimal o2 = operands.next();
	    return o1.multiply(o2);
	  } else if (operator == DIVIDE) {
	    BigDecimal o1 = operands.next();
	    BigDecimal o2 = operands.next();
	    return o1.divide(o2);
	  } else if (operator == EXPONATE) {//TODO
	    BigDecimal o1 = operands.next();
		BigDecimal o2 = operands.next();
		return o1.multiply(o2);
	  } else {
	       return super.evaluate(operator, operands, evaluationContext) ;
	  }
	}
}
