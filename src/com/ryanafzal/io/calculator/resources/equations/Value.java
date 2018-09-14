package com.ryanafzal.io.calculator.resources.equations;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class Value implements ILaTeXValue {
	
	private double value;
	
	public Value(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	protected void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getValue() + "";
	}
	
	@Override
	public String toString() {
		return this.getValue() + "";
	}
	
	private static Value[] merge(Value[] a, Value[] b) {
        Value[] c = new Value[a.length + b.length];
        
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if (i >= a.length) {
            	c[k] = b[j++];
            } else if (j >= b.length) {
            	c[k] = a[i++];
        	} else if (a[i].getValue() <= b[j].getValue()) {
        		c[k] = a[i++];
        	} else{
        		c[k] = b[j++];
        	}
        }
        
        return c;
    }

    public static Value[] mergeSort(Value[] input) {
        int N = input.length;
        
        if (N <= 1) {
        	return input;
        }
        
        Value[] a = new Value[N / 2];
        Value[] b = new Value[N - (N / 2)];
        
        for (int i = 0; i < a.length; i++) {
            a[i] = input[i];
        }
        
        for (int i = 0; i < b.length; i++) {
            b[i] = input[i + (N / 2)];
        }
        
        return merge(mergeSort(a), mergeSort(b));
    }
    
    @Override
    public boolean isMath() {
    	return true;
    }
	
}
