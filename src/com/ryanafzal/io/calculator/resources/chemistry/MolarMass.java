package com.ryanafzal.io.calculator.resources.chemistry;

import java.util.HashMap;

public class MolarMass {
	
	private static HashMap<String, Double> masses = new HashMap<String, Double>();
	
	static {
		masses.put("H", 1.0079);
		masses.put("He", 4.0026);
		masses.put("Li", 6.941);
		masses.put("Be", 9.0122);
		masses.put("B", 10.811);
		masses.put("C", 12.0107);
		masses.put("N", 14.0067);
		masses.put("O", 15.9994);
		masses.put("F", 18.9984);
		masses.put("Ne", 20.1797);
		masses.put("Na", 22.9897);
		masses.put("Mg", 24.305);
		masses.put("Al", 26.9815);
		masses.put("Si", 28.0855);
		masses.put("P", 30.9738);
		masses.put("S", 32.065);
		masses.put("Cl", 35.453);
		masses.put("K", 39.0983);
		masses.put("Ar", 39.948);
		masses.put("Ca", 40.078);
		masses.put("Sc", 44.9559);
		masses.put("Ti", 47.867);
		masses.put("V", 50.9415);
		masses.put("Cr", 51.9961);
		masses.put("Mn", 54.938);
		masses.put("Fe", 55.845);
		masses.put("Ni", 58.6934);
		masses.put("Co", 58.9332);
		masses.put("Cu", 63.546);
		masses.put("Zn", 65.39);
		masses.put("Ga", 69.723);
		masses.put("Ge", 72.64);
		masses.put("As", 74.9216);
		masses.put("Se", 78.96);
		masses.put("Br", 79.904);
		masses.put("Kr", 83.8);
		masses.put("Rb", 85.4678);
		masses.put("Sr", 87.62);
		masses.put("Y", 88.9059);
		masses.put("Zr", 91.224);
		masses.put("Nb", 92.9064);
		masses.put("Mo", 95.94);
		masses.put("Tc", 98.0);
		masses.put("Ru", 101.07);
		masses.put("Rh", 102.9055);
		masses.put("Pd", 106.42);
		masses.put("Ag", 107.8682);
		masses.put("Cd", 112.411);
		masses.put("In", 114.818);
		masses.put("Sn", 118.71);
		masses.put("Sb", 121.76);
		masses.put("I", 126.9045);
		masses.put("Te", 127.6);
		masses.put("Xe", 131.293);
		masses.put("Cs", 132.9055);
		masses.put("Ba", 137.327);
		masses.put("La", 138.9055);
		masses.put("Ce", 140.116);
		masses.put("Pr", 140.9077);
		masses.put("Nd", 144.24);
		masses.put("Pm", 145.0);
		masses.put("Sm", 150.36);
		masses.put("Eu", 151.964);
		masses.put("Gd", 157.25);
		masses.put("Tb", 158.9253);
		masses.put("Dy", 162.5);
		masses.put("Ho", 164.9303);
		masses.put("Er", 167.259);
		masses.put("Tm", 168.9342);
		masses.put("Yb", 173.04);
		masses.put("Lu", 174.967);
		masses.put("Hf", 178.49);
		masses.put("Ta", 180.9479);
		masses.put("W", 183.84);
		masses.put("Re", 186.207);
		masses.put("Os", 190.23);
		masses.put("Ir", 192.217);
		masses.put("Pt", 195.078);
		masses.put("Au", 196.9665);
		masses.put("Hg", 200.59);
		masses.put("Tl", 204.3833);
		masses.put("Pb", 207.2);
		masses.put("Bi", 208.9804);
		masses.put("Po", 209.0);
		masses.put("At", 210.0);
		masses.put("Rn", 222.0);
		masses.put("Fr", 223.0);
		masses.put("Ra", 226.0);
		masses.put("Ac", 227.0);
		masses.put("Pa", 231.0359);
		masses.put("Th", 232.0381);
		masses.put("Np", 237.0);
		masses.put("U", 238.0289);
		masses.put("Am", 243.0);
		masses.put("Pu", 244.0);
		masses.put("Cm", 247.0);
		masses.put("Bk", 247.0);
		masses.put("Cf", 251.0);
		masses.put("Es", 252.0);
		masses.put("Fm", 257.0);
		masses.put("Md", 258.0);
		masses.put("No", 259.0);
		masses.put("Lr", 262.0);
	}
	
	public static double getMolarMassFromSymbol(String symbol) {
		return masses.get(symbol);
	}
	
}
