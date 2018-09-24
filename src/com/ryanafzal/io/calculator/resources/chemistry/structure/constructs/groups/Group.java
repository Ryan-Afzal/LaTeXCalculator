package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

import java.util.ArrayList;
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
	
	/**
	 * 
	 * @param group The group to attach.
	 * @param RIndex The index of the R placeholder on the new group.
	 * @param atomIndex The index of the atom or R placeholder on this group. If it is an atom, then it will be replaced. 
	 */
	public void attachGroup(Group group, int RIndex, int atomIndex) throws StructuralException {
		
		if (!(this.subs[atomIndex] instanceof RConstruct)) {
			this.subs[atomIndex] = new RConstruct();
		}
		
		//Make two lists, one for each Constructs array.
		ArrayList<SubstituentConstruct> subs_this = new ArrayList<SubstituentConstruct>(Arrays.asList(this.subs));
		List<SubstituentConstruct> subs_other = Arrays.asList(group.getConstructs());
		
		//Make two lists, one for each BondConstructs array.
		ArrayList<BondConstruct> bonds_this = new ArrayList<BondConstruct>(Arrays.asList(this.bonds));
		List<BondConstruct> bonds_other = Arrays.asList(group.getBondConstructs());
		
		RIndex = RIndex + subs_other.size();
		
		subs_this.addAll(subs_other);
		bonds_this.addAll(bonds_other);
		
		//Find the bond that is attached to atomIndex
		BondConstruct bond_from_this = bonds_this.stream().filter(bond -> bond.getAtom1() == atomIndex || bond.getAtom2() == atomIndex).findFirst().orElse(null);
		
		if (bond_from_this == null) {
			throw new StructuralException();
		}
		
		int attachment_point_this;
		
		if (bond_from_this.getAtom1() == atomIndex) {
			attachment_point_this = bond_from_this.getAtom2();
		} else {
			attachment_point_this = bond_from_this.getAtom1();
		}
		
		//Find the bond that is attached to RIndex
		BondConstruct bond_from_other = bonds_this.stream().filter(bond -> bond.getAtom1() == atomIndex || bond.getAtom2() == atomIndex).findFirst().orElse(null);
		
		if (bond_from_other == null) {
			throw new StructuralException();
		}
		
		int attachment_point_other;
		
		if (bond_from_other.getAtom1() == atomIndex) {
			attachment_point_other = bond_from_other.getAtom2();
		} else {
			attachment_point_other = bond_from_other.getAtom1();
		}
		
		//bonds_this.remove(bond_from_this);
		
	}
	
	/**
	 * Creates a new <tt>Chemical</tt> from this <tt>Group</tt>.
	 * @return Returns a new <tt>Chemical</tt> represented by this <tt>Groups</tt>
	 * @throws StructuralException If there is still an <tt>RConstruct</tt> in this <tt>Group</tt>.
	 */
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
