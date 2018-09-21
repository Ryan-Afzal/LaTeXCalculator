package com.ryanafzal.io.calculator.resources.chemistry.structure;

public abstract class AtomCharges {
	
	private static int[][] charges = {
			{1},
			{0},
			{1},
			{2},
			{3, -3},
			{-4},
			{-3},
			{-2},
			{-1},
			{0},
	};
	
	public static int[] getChargesForAtom(AtomType atom) {
		return charges[atom.getAtomicNumber() - 1];
	}
	
}
