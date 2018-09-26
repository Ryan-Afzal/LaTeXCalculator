package com.ryanafzal.io.calculator.resources.chemistry.structure;

import com.ryanafzal.io.calculator.resources.chemistry.structure.constructs.groups.Group;

public abstract class IUPACNames {
	
	public static Chemical getCovalentChemicalFromIUPACName(String name) {
		return null;
	}
	
	public static IonicChemical getIonicChemicalFromIUPACName(String name) {
		return null;
	}
	
	/**
	 * Unless required by the functional group, this does not return Hydrogen atoms for groups such as Amines or Hydrocarbons.
	 * Please call <tt>Group.replaceRValues()</tt>.
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unused")
	private Group getGroupFromName(String name) {
		//Base Case: If 'name' describes only one functional group, 
		if (true) {
			
		}
		
		
		
		return null;
	}
	
}
