package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ryanafzal.io.calculator.resources.chemistry.structure.Atom;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Bond;
import com.ryanafzal.io.calculator.resources.chemistry.structure.Chemical;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.AtomConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.BondConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.RConstruct;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.StructuralException;
import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.SubstituentConstruct;

public class Group {

	SubstituentConstruct[] subs;
	BondConstruct[] bonds;
	
	public Group(SubstituentConstruct[] subs, BondConstruct[] bonds) {
		this.subs = subs;
		this.bonds = bonds;
	}
	
	public SubstituentConstruct[] getConstructs() {
		return this.subs;
	}
	
	public BondConstruct[] getBondConstructs() {
		return this.bonds;
	}
	
	public void attachGroup(Group group, int RIndex, int atomIndex) throws StructuralException {
		SubstituentConstruct[] subs_other = group.getConstructs();
		
		
	}
	
	public Chemical createChemicalFromGroup() throws StructuralException {
		List<SubstituentConstruct> subs_list = Arrays.asList(this.subs);
		
		if (subs_list.stream().filter(sub -> sub instanceof RConstruct).findFirst().orElse(null) != null) {
			throw new StructuralException("A group cannot contain an R construct when converting to a Chemical.");
		}
		
		Atom[] atoms = subs_list
				.stream()
				.map(sub -> (AtomConstruct) sub)
				.map(AtomConstruct::getAtomFromConstruct)
				.collect(Collectors.toList())
				.toArray(new Atom[] {});
		Bond[] bonds = new Bond[this.bonds.length];
		
		for (int i = 0; i < this.bonds.length; i++) {
			bonds[i] = this.bonds[i].getBondFromConstruct(atoms);
		}
		
		return new Chemical(atoms, bonds, 0);
	}

}
