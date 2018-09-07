package com.ryanafzal.io.calculator.resources.units;

import com.ryanafzal.io.calculator.resources.units.prefix.Prefix;

public class FluidVolumeUnit extends Unit {
	
	public FluidVolumeUnit(Prefix prefix) {
		super(prefix);
	}
	
	public FluidVolumeUnit() {
		super(Prefix.NONE);
	}

	@Override
	public String getName() {
		return "liters";
	}

	@Override
	public String getSymbol() {
		return "L";
	}

	public Unit convertToVolume() {
		return new VolumeUnit(this.getPrefix());
	}

}
