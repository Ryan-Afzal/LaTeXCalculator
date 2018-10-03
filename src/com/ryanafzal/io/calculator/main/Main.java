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
			
			Atom[] hydroxideatoms1 = new Atom[] {new Atom(AtomType.HYDROGEN, -1), new Atom(AtomType.OXYGEN, -2)};
			Chemical hydroxide1 = new Chemical(hydroxideatoms1, new Bond[] {new Bond(hydroxideatoms1[0], BondType.SINGLE, hydroxideatoms1[1])}, -1);
			Atom sodium1 = new Atom(AtomType.SODIUM, 1);
			IChemical NaOH = new IonicChemical(new IChemical[] {sodium1}, new IChemical[] {hydroxide1}, new Bond[] {new Bond(sodium1, BondType.IONIC, hydroxide1)});
			
			Atom[] nitrateatoms1 = new Atom[] {new Atom(AtomType.NITROGEN, -3), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2)};
			Chemical nitrate1 = new Chemical(
					nitrateatoms1, 
					new Bond[] {
							new Bond(nitrateatoms1[0], BondType.DOUBLE, nitrateatoms1[1]),
							new Bond(nitrateatoms1[0], BondType.DOUBLE, nitrateatoms1[2]),
							new Bond(nitrateatoms1[0], BondType.SINGLE, nitrateatoms1[3]),
					}, 
					-1);
			Atom[] nitrateatoms2 = new Atom[] {new Atom(AtomType.NITROGEN, -3), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2)};
			Chemical nitrate2 = new Chemical(
					nitrateatoms2, 
					new Bond[] {
							new Bond(nitrateatoms2[0], BondType.DOUBLE, nitrateatoms2[1]),
							new Bond(nitrateatoms2[0], BondType.DOUBLE, nitrateatoms2[2]),
							new Bond(nitrateatoms2[0], BondType.SINGLE, nitrateatoms2[3]),
					}, 
					-1);
			Atom be1 = new Atom(AtomType.BERYLLIUM, 2);
			IChemical be_nitrate  = new IonicChemical(
					new IChemical[] {be1}, 
					new IChemical[] {nitrate1, nitrate2}, 
					new Bond[] {
							new Bond(be1, BondType.IONIC, nitrate1), 
							new Bond(be1, BondType.IONIC, nitrate2)});;
			
			Atom[] nitrateatoms3 = new Atom[] {new Atom(AtomType.NITROGEN, -3), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2), new Atom(AtomType.OXYGEN, -2)};
			Chemical nitrate3 = new Chemical(
					nitrateatoms3, 
					new Bond[] {
							new Bond(nitrateatoms3[0], BondType.DOUBLE, nitrateatoms3[1]),
							new Bond(nitrateatoms3[0], BondType.DOUBLE, nitrateatoms3[2]),
							new Bond(nitrateatoms3[0], BondType.SINGLE, nitrateatoms3[3]),
					}, 
					-1);
			Atom sodium2 = new Atom(AtomType.SODIUM, 1);
			IChemical NaNO3 = new IonicChemical(new IChemical[] {sodium2}, new IChemical[] {nitrate3}, new Bond[] {new Bond(sodium2, BondType.IONIC, nitrate3)});
			
			Atom[] hydroxideatoms2 = new Atom[] {new Atom(AtomType.HYDROGEN, -1), new Atom(AtomType.OXYGEN, -2)};
			Chemical hydroxide2 = new Chemical(hydroxideatoms2, new Bond[] {new Bond(hydroxideatoms2[0], BondType.SINGLE, hydroxideatoms2[1])}, -1);
			Atom[] hydroxideatoms3 = new Atom[] {new Atom(AtomType.HYDROGEN, -1), new Atom(AtomType.OXYGEN, -2)};
			Chemical hydroxide3 = new Chemical(hydroxideatoms3, new Bond[] {new Bond(hydroxideatoms3[0], BondType.SINGLE, hydroxideatoms3[1])}, -1);
			Atom be2 = new Atom(AtomType.BERYLLIUM, 2);
			IChemical be_hydroxide = new IonicChemical(
					new IChemical[] {be2}, 
					new IChemical[] {hydroxide2, hydroxide3}, 
					new Bond[] {
							new Bond(be2, BondType.IONIC, hydroxide2), 
							new Bond(be2, BondType.IONIC, hydroxide3)});
			
			MolecularEquation equation = new MolecularEquation(
					new ChemicalValue[] {
							new ChemicalValue(2, new MoleUnit(), NaOH, ChemicalState.AQUEOUS),
							new ChemicalValue(1, new MoleUnit(), be_nitrate, ChemicalState.AQUEOUS)
					}, 
					new ChemicalValue[] {
							new ChemicalValue(2, new MoleUnit(), NaNO3, ChemicalState.AQUEOUS),
							new ChemicalValue(1, new MoleUnit(), be_hydroxide, ChemicalState.SOLID)
					});
			
			System.out.println(equation.getLaTeXString());
			System.out.println(equation.getCompleteIonicEquation().getLaTeXString());
			
			JFrame frame = new JFrame("LaTeXCalculator Test");
			
			frame.add(new JLabel(new ImageIcon(new Render().getRenderedImage("$$" + equation.getCompleteIonicEquation().getLaTeXString() + "$$"))));
			
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
