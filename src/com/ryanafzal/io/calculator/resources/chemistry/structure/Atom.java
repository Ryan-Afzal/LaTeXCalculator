package com.ryanafzal.io.calculator.resources.chemistry.structure;

public class Atom implements IChemical {
	
	private String name;
	private String symbol;
	private int number;
	private double mass;
	private int charge;
	private double electronegativity;
	
	private ElectronShell[] shells;
	private ElectronShell valence;
	
	
	
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
		private boolean[] electrons;
		
		public ElectronShell(ShellType type, int numElectrons) {
			this.name = type.getName();
			this.electrons = new boolean[type.getNumElectrons()];
			
			for (int i = 0; i < numElectrons; i++) {
				this.electrons[i] = true;
			}
		}
		
	}
	
	public Atom(AtomType atom, int charge) {
		this.name = atom.getName();
		this.symbol = atom.getSymbol();
		this.number = atom.getAtomicNumber();
		this.mass = atom.getAtomicMass();
		this.charge = charge;
		
		
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

	@Override
	public double getMolarMass() {
		return this.getAtomicMass();
	}

	@Override
	public String getMolecularFormula() {
		return this.getSymbol();
	}

	@Override
	public String getEmpiricalFormula() {
		return this.getMolecularFormula();
	}

	@Override
	public String getIUPACName() {
		return this.getMolecularFormula();
	}

	@Override
	public int getCharge() {
		return this.charge;
	}
	
	public void bond(Atom other) {
		
	}
	
}
