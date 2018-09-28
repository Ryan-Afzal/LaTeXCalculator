package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

import java.util.Arrays;

import com.ryanafzal.io.calculator.resources.chemistry.structure.AtomType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.BondType;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.AtomConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.BondConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.RConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.SubstituentConstruct;

/**
 * Represents a hydrocarbon. Used as the backbone of an organic compound.
 * @author s-afzalr
 *
 */
public class Hydrocarbon extends Group {

	private Hydrocarbon(SubstituentConstruct[] subs, BondConstruct[] bonds) {
		super(subs, bonds);
	}
	
	
	
	/**
	 * Generates a new <tt>Hydrocarbon</tt> 
	 * @param num_carbon
	 * @param cyclic
	 * @param carbon_bond_pattern
	 * @return
	 */
	public static Hydrocarbon getHydrocarbon(int num_carbon, boolean cyclic, BondType[] carbon_bond_pattern) {
		
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
		
		if (num_carbon < 1) {
			throw new IllegalArgumentException("A hydrocarbon must contain at least one carbon atom.");
		} else if (num_carbon == 1) {
			return new Hydrocarbon(
					new SubstituentConstruct[] {
						new AtomConstruct(AtomType.CARBON, -4), 
						new RConstruct(),
						new RConstruct(),
						new RConstruct(),
						new RConstruct()},
					new BondConstruct[] {
							new BondConstruct(0, BondType.SINGLE, 1),
							new BondConstruct(0, BondType.SINGLE, 2),
							new BondConstruct(0, BondType.SINGLE, 3),
							new BondConstruct(0, BondType.SINGLE, 4)
					});
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
		
		BondType bond_previous;
		BondType bond_this = carbon_bond_pattern[0];
		
		//If the molecule is cyclic 
		if (cyclic) {
			bond_previous = carbon_bond_pattern[num_carbon - 1];
		} else {
			bond_previous = BondType.SINGLE;
		}
		
		int current_carbon = 0;
		int current_bond = 0;
		int current_R = num_carbon;
		
		int num_attached_r = getNumR(bond_previous, bond_this);
		
		bonds[current_bond] = new BondConstruct(current_carbon, bond_this, current_carbon + 1);
		current_bond++;
		
		for (int i = 0; i < num_attached_r; i++) {
			bonds[current_bond] = new BondConstruct(current_carbon, BondType.SINGLE, current_R);
			current_bond++;
			current_R++;
		}
		
		//Rest of carbon chain
		for (current_carbon = 1; current_carbon < num_carbon - 1; current_carbon++) {
			bond_previous = carbon_bond_pattern[current_carbon - 1];
			bond_this = carbon_bond_pattern[current_carbon];
			
			num_attached_r = getNumR(bond_previous, bond_this);
			
			bonds[current_bond] = new BondConstruct(current_carbon, bond_this, current_carbon + 1);
			current_bond++;
			
			for (int k = 0; k < num_attached_r; k++) {
				bonds[current_bond] = new BondConstruct(current_carbon, BondType.SINGLE, current_R);
				current_bond++;
				current_R++;
			}
		}
		
		//Polish the final carbon, and either attach the two ends, or attach final R-components
		bond_previous = carbon_bond_pattern[num_carbon - 2];
		if (cyclic) {
			bond_this = carbon_bond_pattern[num_carbon - 1];
		} else {
			bond_this = BondType.SINGLE;
		}
		
		num_attached_r = getNumR(bond_previous, bond_this);
		for (int k = 0; k < num_attached_r; k++) {
			bonds[current_bond] = new BondConstruct(current_carbon, BondType.SINGLE, current_R);
			current_bond++;
			current_R++;
		}
		
		if (cyclic) {
			bonds[current_bond] = new BondConstruct(current_carbon, bond_this, 0);
		} else {
			bonds[current_bond] = new BondConstruct(0, BondType.SINGLE, current_R);
			bonds[current_bond + 1] = new BondConstruct(current_carbon, BondType.SINGLE, current_R + 1);
		}
		
		return new Hydrocarbon(subs, bonds);
	}
	
	private static int getNumR(BondType previous_bond, BondType this_bond) {
		if (previous_bond == BondType.SINGLE && this_bond == BondType.SINGLE) {
			return 2;
		} else if ((previous_bond == BondType.SINGLE && this_bond == BondType.DOUBLE) || (previous_bond == BondType.DOUBLE && this_bond == BondType.SINGLE)) {
			return 1;
		} else if (
				(previous_bond == BondType.DOUBLE && this_bond == BondType.DOUBLE) 
				|| (previous_bond == BondType.TRIPLE && this_bond == BondType.SINGLE) 
				|| (previous_bond == BondType.SINGLE && this_bond == BondType.TRIPLE)) {
			
			return 0;
		} else {
			throw new IllegalArgumentException("The bond values " + previous_bond.getBond() + ", "  + this_bond.getBond() + "are invalid.");
		}
		
	}

}
