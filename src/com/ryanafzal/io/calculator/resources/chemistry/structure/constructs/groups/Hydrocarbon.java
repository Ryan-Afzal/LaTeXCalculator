package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

import com.ryanafzal.io.calculator.resources.chemistry.structure.AtomType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.AtomConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.BondConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.RConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.SubstituentConstruct;

public class Hydrocarbon extends Group {

	public Hydrocarbon(SubstituentConstruct[] subs, BondConstruct[] bonds) {
		super(subs, bonds);
	}
	
	public static Hydrocarbon getAliphaticHydrocarbon(int num_carbon, boolean cyclic, int[] num_double, int[] num_triple) {
		
		int num_r = ((num_carbon * 3) - (num_double.length * 2) - (num_triple.length * 3));
		SubstituentConstruct[] subs = new SubstituentConstruct[num_r + num_carbon];
		
		BondConstruct[] bonds;
		
		//If the molecule is cyclic, then there are [atoms] bonds, otherwise there are [atoms - 1] bonds. 
		if (cyclic) {
			bonds = new BondConstruct[subs.length];
		} else {
			bonds = new BondConstruct[subs.length - 1];
		}
		
		//Fill the SubstituentConstructs array with Carbon atoms and R-Placeholders
		for (int i = 0; i < num_carbon; i++) {
			subs[i] = new AtomConstruct(AtomType.CARBON, -4);
		}
		for (int i = num_carbon; i < subs.length; i++) {
			subs[i] = new RConstruct();
		}
		
		
		
		return null;
	}

}
