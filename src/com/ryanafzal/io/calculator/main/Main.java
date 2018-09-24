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
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.AtomConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.BondConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.SubstituentConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.RConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups.Group;
import com.ryanafzal.io.calculator.resources.equations.ChemicalValue;
import com.ryanafzal.io.calculator.resources.equations.LaTeXBlock;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.units.*;
import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		
		/*
		 * Creating display frame
		 */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
		
			/*
			 * Displaying the solved problem
			 */
			
			Group methylgroup = new Group(
					new SubstituentConstruct[] {
							new AtomConstruct(AtomType.HYDROGEN, 1),
							new AtomConstruct(AtomType.HYDROGEN, 1),
							new AtomConstruct(AtomType.HYDROGEN, 1),
							new AtomConstruct(AtomType.CARBON, -4),
							new RConstruct()
					}, 
					new BondConstruct[] {
							new BondConstruct(0, BondType.SINGLE, 3),
							new BondConstruct(1, BondType.SINGLE, 3),
							new BondConstruct(2, BondType.SINGLE, 3),
							new BondConstruct(4, BondType.SINGLE, 3),
					});
			
			Group hydrogengroup = new Group(
					new SubstituentConstruct[] {
							new AtomConstruct(AtomType.HYDROGEN, 1),
							new RConstruct()
							}, 
					new BondConstruct[] {
							new BondConstruct(0, BondType.SINGLE, 1)
							});
			
			methylgroup.attachGroup(hydrogengroup, 1, 4);
			
			Chemical methane = methylgroup.createChemicalFromGroup();
			
			System.out.println(methane.getMolecularFormula());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
