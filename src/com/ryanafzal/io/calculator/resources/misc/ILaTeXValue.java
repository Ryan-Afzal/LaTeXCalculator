package com.ryanafzal.io.calculator.resources.misc;

public interface ILaTeXValue {
	/**
	 * Returns a LaTeX {@code String} representing this object.
	 * @return Returns a LaTeX-Formatted {@code String}
	 */
	public String getLaTeXString();
	public default boolean isMath() {
		return true;
	}
}
