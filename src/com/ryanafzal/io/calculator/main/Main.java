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
			
			
			Group propane = Hydrocarbon.getAliphaticHydrocarbon(2, false, new BondType[] {BondType.SINGLE});
			propane.replaceRValues();
			Chemical propane_chemical = propane.createChemicalFromGroup();
			
			System.out.println(propane_chemical.getLaTeXString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
