package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class VolumeUnit extends Unit {

	public VolumeUnit(Prefix prefix) {
		super(prefix);
	}
	
	public VolumeUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "meters^3";
	}

	@Override
	public String getSymbol() {
		return "m^3";
	}
	
	public Unit convertToFluidVolume() {
		return new FluidVolumeUnit(this.getPrefix());
	}

}
