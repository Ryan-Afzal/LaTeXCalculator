package com.ryanafzal.io.calculator.main;

import javax.swing.*;

import com.ryanafzal.io.calculator.render.Render;
import com.ryanafzal.io.calculator.resources.chemistry.ChemicalState;
import com.ryanafzal.io.calculator.resources.chemistry.equation.ChemicalEquation;
import com.ryanafzal.io.calculator.resources.chemistry.equation.MolecularEquation;
import com.ryanafzal.io.calculator.resources.chemistry.stoichiometry.Stoichiometry;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Atom;
import com.ryanafzal.io.calculator.resources.chemistry.structure.AtomType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Bond;
import com.ryanafzal.io.calculator.resources.chemistry.structure.BondType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IChemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.IonicChemical;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.LaTeXBlock;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.units.*;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		
		Atom[] water_atoms = new Atom[] {new Atom(AtomType.HYDROGEN, 1), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.HYDROGEN, 1)};
		Chemical water = new Chemical(water_atoms, new Bond[] {
				new Bond(water_atoms[0], BondType.SINGLE, water_atoms[1]), 
				new Bond(water_atoms[1], BondType.SINGLE, water_atoms[2])}, 0);
		
		/*//Calculator calculator = new Calculator();
		
		
		 * Declaring Oxygen
		 
		Atom[] oxygen_atoms = new Atom[] {new Atom(AtomType.OXYGEN), new Atom(AtomType.OXYGEN)};
		Chemical oxygen = new Chemical(oxygen_atoms, new Bond[]{
				new Bond(oxygen_atoms[0], BondType.DOUBLE, oxygen_atoms[1])}, 0);
		
		
		 * Declaring Carbon Dioxide
		 
		Atom[] co2_atoms = new Atom[] {new Atom(AtomType.OXYGEN), new Atom(AtomType.CARBON), new Atom(AtomType.OXYGEN)};
		Chemical co2 = new Chemical(co2_atoms, new Bond[] {
				new Bond(co2_atoms[0], BondType.DOUBLE, co2_atoms[1]), 
				new Bond(co2_atoms[1], BondType.DOUBLE, co2_atoms[2])}, 0);
		
		
		 * Declaring Atoms in Propane
		 
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
		
		
		 * Declaring Bonds in Propane
		 
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
		
		
		 * Declaring Propane
		 
		Chemical propane = new Chemical(atoms, bonds, 0);
		
		
		 * Creating starting value, 1 mol C3H8
		 
		ChemicalValue start = new ChemicalValue(propane.getMolarMass(), new QuantityUnit(Prefix.KILO), propane, ChemicalState.GAS);
		
		ChemicalValue react1 = new ChemicalValue(propane.getMolarMass() * 0.99, new QuantityUnit(Prefix.NONE), propane, ChemicalState.GAS);
		ChemicalValue react2 = new ChemicalValue(oxygen.getMolarMass(), new QuantityUnit(Prefix.NONE), oxygen, ChemicalState.GAS);
		
		
		 * Creating Chemical Equation, C3H8 + 5O2 -> 4CO2 + 3H2O
		 
		MolecularEquation equation = new MolecularEquation(
				new ChemicalValue[] {
						new ChemicalValue(1, new MoleUnit(), propane, ChemicalState.GAS),
						new ChemicalValue(5, new MoleUnit(), oxygen, ChemicalState.GAS)}, 
				new ChemicalValue[] {
						new ChemicalValue(3, new MoleUnit(), co2, ChemicalState.GAS),
						new ChemicalValue(4, new MoleUnit(), water, ChemicalState.GAS)}
				);*/
		Atom[] NaOH_a = new Atom[] {new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.HYDROGEN, 1)};
		
		IChemical NaOH = new IonicChemical(
				new Atom[] {
						new Atom(AtomType.SODIUM, 1)}, 
				new Chemical[] {
						new Chemical(
								NaOH_a, 
								new Bond[] {new Bond(NaOH_a[0], BondType.SINGLE, NaOH_a[1])}, 
								-1)});
		
		IChemical HCl = new IonicChemical(new Atom[] {new Atom(AtomType.HYDROGEN, 1)}, new Atom[] {new Atom(AtomType.CHLORINE, -1)});
		
		IChemical NaCl = new IonicChemical(new Atom[] {new Atom(AtomType.SODIUM, 1)}, new Atom[] {new Atom(AtomType.CHLORINE, -1)});
		
		ChemicalEquation equation = new MolecularEquation(
				new ChemicalValue[] {
						new ChemicalValue(1, new MoleUnit(), HCl, ChemicalState.AQUEOUS), 
						new ChemicalValue(1, new MoleUnit(), NaOH, ChemicalState.AQUEOUS)
				}, 
				new ChemicalValue[] {
						new ChemicalValue(1, new MoleUnit(), water, ChemicalState.LIQUID), 
						new ChemicalValue(1, new MoleUnit(), NaCl, ChemicalState.AQUEOUS)
				});
		
		/*
		 * Creating the Stoichiometry problem from the equation, with the starting value and equation.
		 */
		Stoichiometry problem = new Stoichiometry(equation);
		
		/*
		 * Creating display frame
		 */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
		
			/*
			 * Solving Stoichiometric ratio between C3H8 and H2O in equation: C3H8 + 5O2 -> 3CO2 + 4H2O, and storing value in a LaTeX-Formatted String.
			 */
			
			RailRoad output = problem.solveFor(new ChemicalValue(10, new MoleUnit(), NaOH, ChemicalState.AQUEOUS), NaCl, new QuantityUnit());
			
			/*
			 * Displaying the solved problem
			 */
			
			frame.add(new JLabel(new ImageIcon(new Render().getRenderedImage("\\[" + output.getLaTeXString() + "\\]"))));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
