package com.ryanafzal.io.calculator.main;

import java.util.ArrayList;

import javax.swing.*;

import com.ryanafzal.io.calculator.render.Render;
import com.ryanafzal.io.calculator.render.RenderException;
import com.ryanafzal.io.calculator.resources.equations.UnitValue;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoad;
import com.ryanafzal.io.calculator.resources.equations.railroad.RailRoadComponent;

public class Main {
	
	public static void main(String[] args) {
		//Calculator calculator = new Calculator();
		
		//Converting milliliters to cubic meters
		UnitValue start = new UnitValue(2500, "mL");
		
		ArrayList<RailRoadComponent> rail = new ArrayList<RailRoadComponent>();
		rail.add(new RailRoadComponent(new UnitValue(1, "L"), new UnitValue(1000, "mL")));
		rail.add(new RailRoadComponent(new UnitValue(1, "m^3"), new UnitValue(1000, "L")));
		
		RailRoad railroad = new RailRoad(start, rail);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			frame.add(new JLabel(new ImageIcon(new Render().getRenderedImage(railroad))));
		} catch (RenderException e) {
			e.printStackTrace();
		}
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
