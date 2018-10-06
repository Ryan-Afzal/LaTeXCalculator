package com.ryanafzal.io.calculator.resources.units;

import java.lang.reflect.InvocationTargetException;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public abstract class Unit implements ILaTeXValue {
	
	private Prefix prefix;
	
	public Unit(Prefix prefix) {
		this.prefix = prefix;
	}
	
	public Prefix getPrefix() {
		return this.prefix;
	}
	
	@Override
	public String getLaTeXString() {
		return this.getPrefix().getSymbol() + this.getSymbol();
	}
	
	@Override
	public boolean isMath() {
		return true;
	}
	
	public abstract String getName();
	public abstract String getSymbol();
	
	public Unit clone(Prefix newPrefix) {
		try {
			return this.getClass().getConstructor(Prefix.class).newInstance(newPrefix);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			return null;
		}
	}
	
	/**
	 * Creates a new {@code Unit} of the same type, with the same {@code Prefix}.
	 * 
	 * @return Returns either a clone of itself, or {@code null}, which should not happen.
	 */
	public Unit clone() {
		try {
			return this.getClass().getConstructor(Prefix.class).newInstance(this.prefix);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			return null;
		}
	}
	
	public static Unit getUnitFromString(String string, Prefix prefix) {
		switch (string) {
		case "g":
			return new QuantityUnit(prefix);
		case "m":
			return new LengthUnit(prefix);
		case "m^3":
			return new VolumeUnit(prefix);
		case "L":
			return new FluidVolumeUnit(prefix);
		case "K":
			return new TemperatureUnit(prefix);
		default:
			throw new IllegalArgumentException("String \"" + string + " \" is not a valid unit.");
		}
	}
	
	public static Unit getUnitFromString(String string) {
		return getUnitFromString(string, Prefix.NONE);
	}
	
}
