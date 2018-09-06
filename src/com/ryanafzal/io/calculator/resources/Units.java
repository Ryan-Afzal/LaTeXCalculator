package com.ryanafzal.io.calculator.resources;

public enum Units {
	
	//Chemistry
	MOLES("mol"),
	
	//Length
	MILLIMETERS("mm"),
	CENTIMETERS("cm"),
	METERS("m"),
	KILOMETERS("km"),
	
	//Weight
	MILLIGRAMS("mg"),
	GRAMS("g"),
	KILOGRAMS("kg"),
	
	//Volume
	MILLILITERS("mL"),
	LITERS("L");
	
	
	private String value;
	
	private Units(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
