package com.ryanafzal.io.calculator.main;

import javax.swing.*;

import com.ryanafzal.io.calculator.render.Render;
import com.ryanafzal.io.calculator.resources.chemistry.Atom;
import com.ryanafzal.io.calculator.resources.chemistry.AtomType;
import com.ryanafzal.io.calculator.resources.chemistry.Bond;
import com.ryanafzal.io.calculator.resources.chemistry.BondType;
import com.ryanafzal.io.calculator.resources.chemistry.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.chemistry.stoichiometry.Stoichiometry;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.units.*;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class Main {
	
	public static void main(String[] args) {
		//Calculator calculator = new Calculator();
		
		/*
		 * Declaring Oxygen
		 */
		Atom[] oxygen_atoms = new Atom[] {new Atom(AtomType.OXYGEN), new Atom(AtomType.OXYGEN)};
		Chemical oxygen = new Chemical(oxygen_atoms, new Bond[]{
				new Bond(oxygen_atoms[0], BondType.DOUBLE, oxygen_atoms[1])});
		
		/*
		 * Declaring Carbon Dioxide
		 */
		Atom[] co2_atoms = new Atom[] {new Atom(AtomType.OXYGEN), new Atom(AtomType.CARBON), new Atom(AtomType.OXYGEN)};
		Chemical co2 = new Chemical(co2_atoms, new Bond[] {
				new Bond(co2_atoms[0], BondType.DOUBLE, co2_atoms[1]), 
				new Bond(co2_atoms[1], BondType.DOUBLE, co2_atoms[2])});
		
		/*
		 * Declaring Water
		 */
		Atom[] water_atoms = new Atom[] {new Atom(AtomType.HYDROGEN), new Atom(AtomType.OXYGEN), new Atom(AtomType.HYDROGEN)};
		Chemical water = new Chemical(water_atoms, new Bond[] {
				new Bond(water_atoms[0], BondType.SINGLE, water_atoms[1]), 
				new Bond(water_atoms[1], BondType.SINGLE, water_atoms[2])});
		
		/*
		 * Declaring Atoms in Propane
		 */
		Atom[] atoms = {
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.HYDROGEN),
				new Atom(AtomType.CARBON),
				new Atom(AtomType.CARBON),
				new Atom(AtomType.CARBON)
		};
		
		/*
		 * Declaring Bonds in Propane
		 */
		Bond[] bonds = {
				new Bond(atoms[0], BondType.SINGLE, atoms[8]),
				new Bond(atoms[1], BondType.SINGLE, atoms[8]),
				new Bond(atoms[2], BondType.SINGLE, atoms[8]),
				new Bond(atoms[3], BondType.SINGLE, atoms[9]),
				new Bond(atoms[4], BondType.SINGLE, atoms[9]),
				new Bond(atoms[5], BondType.SINGLE, atoms[10]),
				new Bond(atoms[6], BondType.SINGLE, atoms[10]),
				new Bond(atoms[7], BondType.SINGLE, atoms[10]),
				new Bond(atoms[8], BondType.SINGLE, atoms[9]),
				new Bond(atoms[9], BondType.SINGLE, atoms[10])
		};
		
		/*
		 * Declaring Propane
		 */
		Chemical propane = new Chemical(atoms, bonds);
		
		/*
		 * Creating starting value, 1 mol C3H8
		 */
		ChemicalValue start = new ChemicalValue(propane.getMolarMass(), new QuantityUnit(Prefix.KILO), propane, ChemicalState.GAS);
		
		/*
		 * Creating Chemical Equation, C3H8 + 5O2 -> 4CO2 + 3H2O
		 */
		ChemicalEquation equation = new ChemicalEquation(
				new ChemicalValue[] {
						new ChemicalValue(1, new MoleUnit(), propane, ChemicalState.GAS),
						new ChemicalValue(5, new MoleUnit(), oxygen, ChemicalState.GAS)}, 
				new ChemicalValue[] {
						new ChemicalValue(3, new MoleUnit(), co2, ChemicalState.GAS),
						new ChemicalValue(4, new MoleUnit(), water, ChemicalState.GAS)}
				);
		
		/*
		 * Creating the Stoichiometry problem from the equation, with the starting value and equation.
		 */
		Stoichiometry problem = new Stoichiometry(equation, start);
		
		/*
		 * Creating display frame
		 */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
		
			/*
			 * Solving Stoichiometric ratio between C3H8 and H2O in equation: C3H8 + 5O2 -> 3CO2 + 4H2O, and storing value in a LaTeX-Formatted String.
			 */
			String LaTeX = "$$" + problem.solveFor(water, QuantityUnit.class).getLaTeXString() + "$$";
		
			/*
			 * Displaying the solved problem
			 */
			
			System.out.println(LaTeX);
			frame.add(new JLabel(new ImageIcon(new Render().getRenderedImage(LaTeX))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
