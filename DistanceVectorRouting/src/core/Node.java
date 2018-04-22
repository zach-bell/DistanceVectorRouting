package core;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Node {
	
	private PApplet launcher;
	private float textSize = 14;
	private boolean locked = false;
	
	public PVector position;
	public String name;
	public static PVector size = new PVector(40, 20);
	public Button nodeButton;
	public int color;
	
	// Router table
	public ArrayList<Node> table = new ArrayList<Node>();
	//	TODO Actually implement the table for each node.
	//	This could solve the removing problem
	
	// Connection table
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public Node(PApplet launcher, String name, PVector position) {
		this.launcher = launcher;
		this.name = name;
		this.position = position;
		
		color = launcher.color(launcher.random(175, 255),launcher.random(175, 255),launcher.random(175, 255));
		
		nodeButton = new Button(launcher, name, position, size);
		nodeButton.noDraw = true;
		
		PApplet.println("Node: "+name+" created. Position: "+position.x+","+position.y);
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
	// Connections
		for (Connection c : connections) {
			c.draw();
		}
	}
	
	public void checkConnections() {
		PApplet.println("Checking...");
		for (Connection c : connections) {
			if (c.node1 == null) {
				connections.remove(c);
				break;
			}
			if (c.node2 == null) {
				connections.remove(c);
				break;
			}
		}
	}
	
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
