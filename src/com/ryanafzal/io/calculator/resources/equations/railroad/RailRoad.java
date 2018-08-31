package com.ryanafzal.io.calculator.resources.equations.railroad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.ryanafzal.io.calculator.resources.ILaTeXValue;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;

public class RailRoad implements ILaTeXValue {
	
	private UnitValue startingValue;
	private ArrayList<RailRoadComponent> rail;
	
	public RailRoad(UnitValue startingValue) {
		this.startingValue = startingValue;
		this.rail = new ArrayList<RailRoadComponent>();
	}
	
	public RailRoad(UnitValue startingValue, ArrayList<RailRoadComponent> rail) {
		this.startingValue = startingValue;
		this.rail = rail;
	}
	
	public RailRoad(UnitValue startingValue, RailRoadComponent[] rail) {
		this.startingValue = startingValue;
		this.rail = new ArrayList<RailRoadComponent>(Arrays.asList(rail));
	}
	
	public void addComponent(RailRoadComponent component) {
		this.rail.add(component);
	}
	
	public void addComponents(Collection<RailRoadComponent> list) {
		this.rail.addAll(list);
	}
	
	public void addComponents(RailRoadComponent[] list) {
		this.addComponents(Arrays.asList(list));
	}
	
	public UnitValue solveRailroad() {
		double top = this.startingValue.getValue();
		double bottom = 1;
		
		String lastunit = this.startingValue.getUnits();
		
		Iterator<RailRoadComponent> iterator = this.rail.iterator();
		while (iterator.hasNext()) {
			RailRoadComponent current = iterator.next();
			top *= current.getNumerator().getValue();
			bottom *= current.getDenominator().getValue();
			
			lastunit = current.getNumerator().getUnits();
		}
		
		return new UnitValue(top / bottom, lastunit);
	}

	@Override
	public String getLaTeXString() {
		String output = startingValue.getLaTeXString();
		
		Iterator<RailRoadComponent> iterator = this.rail.iterator();
		while (iterator.hasNext()) {
			output += ("\\cdot" + iterator.next().getLaTeXString());
		}
		
		output += (" = " + this.solveRailroad().getLaTeXString());
		
		return output;
	}
	
}
