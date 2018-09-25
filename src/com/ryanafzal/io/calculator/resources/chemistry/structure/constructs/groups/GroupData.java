package com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups;

public class GroupData {
	
	private Group[] groups;
	private int[] positions;
	
	public GroupData(Group[] groups, int[] positions) {
		this.groups = groups;
		this.positions = positions;
	}
	
	public Group[] getGroups() {
		return this.groups;
	}
	
	public int[] getPositions() {
		return this.positions;
	}
	
}
