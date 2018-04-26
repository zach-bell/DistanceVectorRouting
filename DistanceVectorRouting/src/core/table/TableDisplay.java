package core.table;

import java.util.ArrayList;
import applet.Launcher;
import core.Main;
import processing.core.PApplet;

public class TableDisplay extends PApplet {
	
	private Launcher launcher;
	private Main main;
	private Table tableToDisplay;
	private ArrayList<TextTableHolders> ttholders;
	
	public boolean active = false;
	
	public TableDisplay(Launcher launcher, Main main) {
		this.launcher = launcher;
		this.main = main;
		
		tableToDisplay = new Table();
		ttholders = new ArrayList<TextTableHolders>();
	}
	public void settings() {
		size(200, 500);
	}
	public void setup() {
		getSurface().setFrameRate(10);
	}
	public void draw() {
		background(0);
		textSize(14);
		fill(color(240,240,240));
		stroke(color(240,240,240));
		textAlign(PApplet.LEFT);
		// checking to see if there's a node that has a table to display
		// and then sets the current visible table to the active nodes table
		if (main.selectedNode != null) {
			tableToDisplay = main.selectedNode.table;
			text(main.selectedNode.name, 0, 15);
		}
		for (TextTableHolders t : ttholders) {
			t.draw();
		}
	}
	public void update() {
		refreshDisplay();
		for (int i = 0; i < tableToDisplay.destinations.size(); i++) {
			ttholders.add(new TextTableHolders(this, tableToDisplay.destinations.get(i), tableToDisplay.delays.get(i), tableToDisplay.outgoing.get(i), 10, i+3, 14));
		}
	}
	public void refreshDisplay() {
		ttholders = new ArrayList<TextTableHolders>();
	}
	public void exit() {
		println("Exiting");
		launcher.toggleTableWindow();
	}
}
