package core.base;

import java.util.ArrayList;
import core.table.Table;
import processing.core.PApplet;
import processing.core.PVector;

public class Node {
	
	private PApplet launcher;
	private float textSize = 14;
	private boolean locked = false;
	private Node dummy;
	
	public PVector position;
	public String name;
	public static PVector size = new PVector(40, 20);
	public Button nodeButton;
	public int color;
	
	// Connections for easy removal
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	
	// Router table
	public Table table;
	
	public Node(PApplet launcher, String name, PVector position) {
		this.launcher = launcher;
		this.name = name;
		this.position = position;
		
		color = launcher.color(launcher.random(175, 255),launcher.random(175, 255),launcher.random(175, 255));
		
		nodeButton = new Button(launcher, name, position, size);
		nodeButton.noDraw = true;
		
		table = new Table();
		
		PApplet.println("Node: "+name+" created. Position: "+position.x+","+position.y);
	}
	
	public Node(PApplet launcher, Node dummy, String name, PVector position) {
		this.launcher = launcher;
		this.name = name;
		this.position = position;
		this.dummy = dummy;
		
		color = launcher.color(launcher.random(175, 255),launcher.random(175, 255),launcher.random(175, 255));
		
		nodeButton = new Button(launcher, name, position, size);
		nodeButton.noDraw = true;
		
		table = new Table();
		
		PApplet.println("Node: "+name+" created. Position: "+position.x+","+position.y);
	}
	public void newTable(ArrayList<Node> nodes) {
		table = new Table();
		PApplet.println("Populated table for "+name);
		for (Node n : nodes) {
			table.destinations.add(n);
			table.delays.add(10000);
			table.outgoing.add(dummy);
		}
		int i = table.destinations.indexOf(this);
		table.delays.set(i, 0);
	}
	public void draw() {
	// Rectangle
		launcher.rectMode(PApplet.CORNER);
		launcher.fill(color);
		launcher.rect(position.x, position.y, size.x, size.y);
	// Text
		launcher.fill(0,0,0);
		launcher.textAlign(PApplet.LEFT);
		launcher.text(name, position.x + 2, position.y + (textSize));
	}
	// Mouse pressed kid
	public void mousePressed() {
		locked = true;
	}
	public void mouseDragged() {
		if (locked) {
			position.x = launcher.mouseX - (size.x / 2);
			position.y = launcher.mouseY - (size.y / 2);
		}
	}
	public void mouseReleased() {
		locked = false;
	}
}
