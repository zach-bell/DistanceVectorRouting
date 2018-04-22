package core;

import processing.core.PApplet;
import processing.core.PVector;

public class Connection {
	
	private PApplet launcher;
	private PVector pos1 = new PVector();
	private PVector pos2 = new PVector();
	
	public Node node1;
	public Node node2;
	public int color = 0;
	
	public Connection(PApplet launcher, Node node1, Node node2, int color) {
		this.launcher = launcher;
		this.node1 = node1;
		this.node2 = node2;
		this.color = color;
		PApplet.println("Connection between: "+node1.name+" & "+node2.name);
	}
	
	public void draw() {
		// update positions of nodes
		grabPositions();
		// Line
		launcher.stroke(color);
		launcher.line(pos1.x, pos1.y, pos2.x, pos2.y);
		// Text
		launcher.fill(color);
		launcher.textSize(12);
		launcher.text(""+distancePoint(node1.position, node2.position),
						midPoint(node1.position, node2.position).x,
						midPoint(node1.position, node2.position).y);
		launcher.noStroke();
	}
	
	/*	grabPositions()
	 *  Will try to get the current positions of the nodes.
	 *  If one is null then nothing updates.
	 */
	
	private void grabPositions() {
		pos1.x = node1.position.x + (Node.size.x / 2);
		pos1.y = node1.position.y + (Node.size.y / 2);
		pos2.x = node2.position.x + (Node.size.x / 2);
		pos2.y = node2.position.y + (Node.size.y / 2);
	}
	
	// Literally the distance formula
	private int distancePoint(PVector pos1, PVector pos2) {
		return (int) PApplet.sqrt((PApplet.sq(pos2.x - pos1.x) + PApplet.sq(pos2.y - pos1.y)));
	}
	
	// Literally the midpoint formula
	private PVector midPoint(PVector pos1, PVector pos2) {
		return new PVector(((pos1.x + pos2.x) / 2),((pos1.y + pos2.y) / 2));
	}
}
