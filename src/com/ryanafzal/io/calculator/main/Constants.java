package com.ryanafzal.io.calculator.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Constants and various helper methods.
 * @author s-afzalr
 *
 */
public abstract class Constants {
	
	public static final String TITLE = "Advanced Calculator";
	public static final double WIDTH = 1000;
	public static final double HEIGHT = 750;
	
	public static String FILEPATH = "C:/Users/s-afzalr/Documents/LaTeXCalculator/";
	public static final String EXPERIMENT_FILE_EXTENSION = "exp";
	
	public static final char COMMAND_OPERATOR = '-';
	public static final String COMMAND_CARAT = ">>";
	
	/**
	 * Returns <tt>true</tt> if the input has at least one alphabetic character.
	 * @param string The input string.
	 * @return <tt>true</tt> if the input has at least one alphabetic character, and <tt>false</tt> otherwise
	 */
	public static boolean hasAlphabeticCharacter(String string) {
		return string.chars().anyMatch(Character::isAlphabetic);
	}
	
	/**
	 * Returns <tt>true</tt> if the input is comprised of only letters and numbers.
	 * @param string The input string.
	 * @return <tt>true</tt> if the input is a valid variable name, and <tt>false</tt> otherwise
	 */
	public static boolean isValidVariableName(String string) {
		return string.chars().allMatch(Character::isLetterOrDigit);
	}
	
	/**
	 * Returns <tt>true</tt> if the input is surrounded by '[' and ']'.
	 * @param string The input string.
	 * @return <tt>true</tt> if the input is a valid chemical input, and <tt>false</tt> otherwise
	 */
	public static boolean isValidChemicalInput(String string) {
		return (string.indexOf("[") == 0 && string.indexOf("]") == string.length() - 1);
	}
	
	public static boolean isMolecularFormula(String string) {		
		return string.chars().allMatch(Character::isLetterOrDigit);
	}
	
	public static String[] splitString(String input, String regex) {
		ArrayList<String> strings = new ArrayList<String>();

	    Matcher m = Pattern.compile(regex).matcher(input);
	    while (m.find()) {
	        if (m.group(1) != null) {
	        	strings.add(m.group(1));
	        } else {
	            strings.add(m.group(2));
	        }
	    }
	    
	    return strings.toArray(new String[] {});
	}
	
}
