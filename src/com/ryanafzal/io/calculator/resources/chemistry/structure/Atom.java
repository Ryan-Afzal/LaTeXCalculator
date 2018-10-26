package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Atom implements IChemical {
	
	/*
	 * Prerequesites for electron storage
	 * 
	 * Easily obtain which bonds of which types, to which atoms
	 * Stored in valence shell
	 * 
	 * ON: Oxidation Number
	 * VE: Total # of valence electrons
	 * LP: # of lone pairs
	 * BE: # of bonded electrons belonging to this atom (electronegativity)
	 * 
	 * Easily calculate oxidation number : ON = VE - (LP + BE)
	 * 
	 * 
	 * IMPLEMENTATIONS:
	 * 1. Store bonds in the electrons, and iterate over the array to get all bonds. 
	 * 2. Cache bonds in objects, 
	 * 
	 */
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	private double electronegativity;//TODO
	
	private int oxidation_state;//TODO Cached
	
	private ElectronShell valence;//TODO
	
	
	
	private enum ShellType {
		
		S("s", 0),
		P("p", 1),
		D("d", 2),
		F("f", 3),
		G("g", 4),
		H("h", 5),
		I("i", 6);
		
		private String name;
		private int azimuthal_quantum;
		private int num_electrons;
		
		ShellType(String name, int aq) {
			this.name = name;
			this.azimuthal_quantum = aq;
			this.num_electrons = 2 * ((2 * this.azimuthal_quantum) + 1);
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getNumElectrons() {
			return this.num_electrons;
		}
		
	}
	
	private class ElectronShell {
		
		private String name;
		private Atom parent;
		private Electron[] electrons;
		
		public ElectronShell(Atom parent, ShellType type, int numElectrons) {
			this.name = type.getName();
			this.parent = parent;
			this.electrons = new Electron[type.getNumElectrons()];
			
			for (int i = 0; i < numElectrons; i++) {
				this.electrons[i] = new Electron(parent);
			}
		}
		
	}
	
	public Atom(AtomType atom, int charge) {
		this.name = atom.getName();
		this.symbol = atom.getSymbol();
		this.number = atom.getAtomicNumber();
		this.mass = atom.getAtomicMass();
		
		this.valence = new ElectronShell(this, ShellType.S, 1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public int getAtomicNumber() {
		return this.number;
	}
	
	public double getAtomicMass() {
		return this.mass;
	}

	@Override
	public String getLaTeXString() {
		return this.getMolecularFormula();
	}

	@Deprecated
	@Override
	public double getMolarMass() {
		return this.getAtomicMass();
	}

	@Deprecated
	@Override
	public String getMolecularFormula() {
		return this.getSymbol();
	}

	@Deprecated
	@Override
	public String getEmpiricalFormula() {
		return this.getMolecularFormula();
	}

	@Deprecated
	@Override
	public String getIUPACName() {
		return this.getMolecularFormula();
	}

	@Deprecated
	@Override
	public int getCharge() {
		return 0;
	}
	
	private boolean bond(Atom other) {
		recalculateOxidationState();
		return true;
	}
	
	private void recalculateOxidationState() {
		
	}
	
	public static boolean bond(Atom atom1, Atom atom2) {
		return atom1.bond(atom2) && atom2.bond(atom1);
	}
}
