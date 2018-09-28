package com.ryanafzal.io.calculator.main;

import java.awt.image.BufferedImage;

import javax.swing.*;

import com.ryanafzal.io.calculator.render.Render;
import com.ryanafzal.io.calculator.resources.chemistry.*;
import com.ryanafzal.io.calculator.resources.chemistry.equation.*;
import com.ryanafzal.io.calculator.resources.chemistry.stoichiometry.*;
import com.ryanafzal.io.calculator.resources.chemistry.structure.*;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.*;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups.*;
import com.ryanafzal.io.calculator.resources.equations.*;
import com.ryanafzal.io.calculator.resources.units.*;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		try {
			
			Chemical propane = Hydrocarbon
					.getHydrocarbon(3, false, new BondType[] {BondType.SINGLE, BondType.SINGLE})
					.replaceRValues()
					.createChemicalFromGroup();
			
			Atom[] atoms_oxygen = {
					new Atom(AtomType.OXYGEN, -2),
					new Atom(AtomType.OXYGEN, -2)
			};
			
			Chemical oxygen = new Chemical(atoms_oxygen, new Bond[] {new Bond(atoms_oxygen[0], BondType.SINGLE, atoms_oxygen[1])}, 0);
			
			Atom[] atoms_water = {
					new Atom(AtomType.HYDROGEN, 1),
					new Atom(AtomType.HYDROGEN, 1),
					new Atom(AtomType.OXYGEN, -2)
			};
			
			Chemical water = new Chemical(
					atoms_water, 
					new Bond[] {
							new Bond(atoms_water[0], BondType.SINGLE, atoms_water[2]),
							new Bond(atoms_water[1], BondType.SINGLE, atoms_water[2])
							}, 
					0
				);
			
			Atom[] atoms_co2 = {
					new Atom(AtomType.OXYGEN, -2),
					new Atom(AtomType.OXYGEN, -2),
					new Atom(AtomType.CARBON, -4)
			};
			
			Chemical co2 = new Chemical(
					atoms_co2, 
					new Bond[] {
							new Bond(atoms_co2[0], BondType.DOUBLE, atoms_co2[2]),
							new Bond(atoms_co2[1], BondType.DOUBLE, atoms_co2[2])
							}, 
					0
				);
			
			ChemicalEquation equation = new MolecularEquation(
					new ChemicalValue[] {
							new ChemicalValue(1, new MoleUnit(), propane, ChemicalState.GAS),
							new ChemicalValue(5, new MoleUnit(), oxygen, ChemicalState.GAS)
					}, 
					new ChemicalValue[] {
							new ChemicalValue(3, new MoleUnit(), co2, ChemicalState.GAS),
							new ChemicalValue(4, new MoleUnit(), water, ChemicalState.GAS)
					});
			
			Stoichiometry stoichiometry = new Stoichiometry(equation);
			
			JFrame frame = new JFrame("LaTeXCalculator Test");
			
			BufferedImage image = new Render()
					.getRenderedImage(
							"$$" + stoichiometry
								.solveFor(
										new ChemicalValue(
												100, 
												new QuantityUnit(), 
												propane, 
												ChemicalState.GAS), 
										water, 
										new QuantityUnit())
								.getLaTeXString()
							+ "$$");
			
			frame.add(new JLabel(new ImageIcon(image)));
			
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
