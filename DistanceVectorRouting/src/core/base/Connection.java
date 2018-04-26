package core.base;

import processing.core.PApplet;
import processing.core.PVector;

public class Connection {
	
	private PApplet launcher;
	private PVector pos1 = new PVector();
	private PVector pos2 = new PVector();
	private String text = "";
	private PVector size = new PVector(30, 16);
	
	public Node node1;
	public Node node2;
	public Button connectionButton;
	public int color = 0;
	public int mid;
	
	public Connection(PApplet launcher, Node node1, Node node2, int color) {
		this.launcher = launcher;
		this.node1 = node1;
		this.node2 = node2;
		this.color = color;
		
		PApplet.println("Connection between: "+node1.name+" & "+node2.name);
		mid = distancePoint(node1.position, node2.position);
		connectionButton = new Button(launcher, text, new PVector(), size, launcher.color(230, 120, 120), color);
		connectionButton.noFill = true;
	}
	
	public void draw() {
		// update positions of nodes
		grabPositions();
		mid = distancePoint(node1.position, node2.position);
		connectionButton.position = midPoint(node1.position, node2.position).add(0, -8);
		connectionButton.text = ""+distancePoint(node1.position, node2.position);
		// Line
		launcher.stroke(color);
		launcher.line(pos1.x, pos1.y, pos2.x, pos2.y);
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
