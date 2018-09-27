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
			
			Group hydrocarbon = Hydrocarbon.getAliphaticHydrocarbon(
				4, 
				true, 
				new BondType[] {
					BondType.SINGLE,
					BondType.DOUBLE, 
					BondType.SINGLE, 
					BondType.SINGLE}
				);
			hydrocarbon.replaceRValues();
			Chemical propane_chemical = hydrocarbon.createChemicalFromGroup();
			
			System.out.println(propane_chemical.getLaTeXString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
