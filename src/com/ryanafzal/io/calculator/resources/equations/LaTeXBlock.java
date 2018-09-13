package com.ryanafzal.io.calculator.resources.equations;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.ryanafzal.io.calculator.ILaTeXMultiple;
import com.ryanafzal.io.calculator.resources.ILaTeXValue;

public class LaTeXBlock implements ILaTeXMultiple {

	private ILaTeXValue[] parts;
	
	public LaTeXBlock(ILaTeXValue[] parts) {
		this.parts = parts;
	}
	
	@Override
	public String[] getLaTeXStrings() {
		return Arrays.asList(this.parts).stream().map(ILaTeXValue::getLaTeXString).collect(Collectors.toList()).toArray(new String[] {});
	}

}
