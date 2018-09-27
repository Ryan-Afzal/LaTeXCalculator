package com.ryanafzal.io.calculator.main;

import javax.swing.*;

import com.ryanafzal.io.calculator.resources.chemistry.structure.BondType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.StructuralException;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups.Group;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups.Hydrocarbon;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		try {
			
			Group hydrocarbon = Hydrocarbon.getHydrocarbon(
				6, 
				true, 
				new BondType[] {
					BondType.SINGLE,
					BondType.DOUBLE, 
					BondType.SINGLE, 
					BondType.DOUBLE,
					BondType.SINGLE,
					BondType.DOUBLE,}
				);
			hydrocarbon.replaceRValues();
			Chemical chemical = hydrocarbon.createChemicalFromGroup();
			
			System.out.println(chemical.getLaTeXString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
