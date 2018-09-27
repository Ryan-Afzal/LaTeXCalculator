package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

import java.util.Arrays;

import com.ryanafzal.io.calculator.resources.chemistry.structure.AtomType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.BondType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.AtomConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.BondConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.RConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.SubstituentConstruct;

public class Hydrocarbon extends Group {

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * REPLACE WITH CH3-CH2-CH3 Style BONDING SYSTEM!
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private Hydrocarbon(SubstituentConstruct[] subs, BondConstruct[] bonds) {
		super(subs, bonds);
	}
	
	public static Hydrocarbon getAliphaticHydrocarbon(int num_carbon, boolean cyclic, BondType[] carbon_bond_pattern) {
		
		if (cyclic && num_carbon < 3) {
			throw new IllegalArgumentException("A cyclic hydrocarbon must have at least 3 carbon atoms.");
		}
		
		int num_double = 0;
		int num_triple = 0;
		
		int expected_pattern_size;
		if (cyclic) {
			expected_pattern_size = num_carbon;
		} else {
			expected_pattern_size = num_carbon - 1;
		}
		
		if (carbon_bond_pattern.length != expected_pattern_size) {
			throw new IllegalArgumentException("The supplied bond pattern of " + Arrays.toString(carbon_bond_pattern) + " is not the correct length.");
		}
		
		for (BondType i : carbon_bond_pattern) {
			if (i == BondType.TRIPLE) {
				num_triple++;
			} else if (i == BondType.DOUBLE) {
				num_double++;
			} else if (i != BondType.SINGLE) {
				throw new IllegalArgumentException("The supplied bond pattern of " + Arrays.toString(carbon_bond_pattern) + " is invalid.");
			}
		}
		
		int num_r = ((num_carbon * 2) - (num_double * 2) - (num_triple * 4));
		if (!cyclic) {
			num_r += 2;
		}
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
		
		
		int start = num_carbon;
		int bond = 0;
		//Attach the carbon atoms to their corresponding values
		for (int c = 0; c < num_carbon - 1; c++) {
			int depth;
			BondType bond_pattern = carbon_bond_pattern[c];
			if (bond_pattern == BondType.TRIPLE) {
				depth = 0;
			} else if (bond_pattern == BondType.DOUBLE) {
				depth = 1;
			} else {
				depth = 2;
			}
			
			bonds[bond] = new BondConstruct(c, bond_pattern, c + 1);
			bond++;
			
			for (int i = start; i < start + depth; i++) {
				bonds[bond] = new BondConstruct(c, BondType.SINGLE, i);
				bond++;
			}
			
			start += depth;
		}
		
		if (cyclic) {
			int c = num_carbon - 1;
			int depth;
			BondType bond_pattern = carbon_bond_pattern[c];
			if (bond_pattern == BondType.TRIPLE) {
				depth = 0;
			} else if (bond_pattern == BondType.DOUBLE) {
				depth = 1;
			} else {
				depth = 2;
			}
			
			bonds[bond] = new BondConstruct(c, bond_pattern, 0);
			bond++;
			
			for (int i = start; i < start + depth; i++) {
				bonds[bond] = new BondConstruct(c, BondType.SINGLE, i);
				bond++;
			}
			
			start += depth;
		} else if (num_carbon == 1) {
			for (int i = start; i < start + 2; i++) {
				bonds[bond] = new BondConstruct(0, BondType.SINGLE, i);
				bond++;
			}
			
			bonds[bonds.length - 2] = new BondConstruct(0, BondType.SINGLE, subs.length - 2);
			bonds[bonds.length - 1] = new BondConstruct(num_carbon - 1, BondType.SINGLE, subs.length - 1);
		} else {
			int c = num_carbon - 2;
			int depth;
			BondType bond_pattern = carbon_bond_pattern[c];
			if (bond_pattern == BondType.TRIPLE) {
				depth = 0;
			} else if (bond_pattern == BondType.DOUBLE) {
				depth = 1;
			} else {
				depth = 2;
			}
			
			c++;
			
			for (int i = start; i < start + depth; i++) {
				bonds[bond] = new BondConstruct(c, BondType.SINGLE, i);
				bond++;
			}
			bonds[bonds.length - 2] = new BondConstruct(0, BondType.SINGLE, subs.length - 2);
			bonds[bonds.length - 1] = new BondConstruct(num_carbon - 1, BondType.SINGLE, subs.length - 1);
		}
		
		return new Hydrocarbon(subs, bonds);
	}

}
