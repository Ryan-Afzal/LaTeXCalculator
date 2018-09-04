package com.ryanafzal.io.calculator.resources.chemistry;

public enum BondType {
	
	SINGLE(1),
	DOUBLE(2),
	TRIPLE(3);
	
	private int bond;
	
	private BondType(int bond) {
		this.bond = bond;
	}
	
	public int getBond() {
		return this.bond;
	}
	
}
