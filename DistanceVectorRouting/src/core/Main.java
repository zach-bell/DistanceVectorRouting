package core;

import java.util.ArrayList;

import applet.GUIelements;
import processing.core.PApplet;
import processing.core.PVector;
import tools.RandomGen;

public class Main {

	private PApplet launcher;
	private RandomGen gen = new RandomGen();
	private ArrayList<Node> nodes;
	private GUIelements gui;
	private Node selectedNode;
	
	public Main(PApplet launcher) {
		this.launcher = launcher;
	}
	
	// Calls setup which runs after processing's libraries
	public void setup() {
		nodes = new ArrayList<Node>();
		gui = new GUIelements(launcher);
		gui.setup();
	}
	
	// Draws visual elements on the screen specific to this class
	public void draw() {
		for (Node n : nodes) {
			if (n.equals(selectedNode))
				launcher.stroke(255,0,0);
			else
				launcher.noStroke();
			n.draw();
		}
		launcher.noStroke();
		gui.draw();
	}
	
/*	createNode()
 * 		Creates a new node somewhere random in the drawing window.
 * 		Each node can be interacted with left and right mouse clicks.
 */
	public void createNode() {
		Node temp = new Node(launcher, gen.nextChar()+gen.nextNumberString(3),
				new PVector((int) (launcher.random(Node.size.x, launcher.width - Node.size.x)),
						(int) (launcher.random(Node.size.y, launcher.height - Node.size.y))));
		selectedNode = temp;
		nodes.add(temp);
	}

	// Will run if the mouse is clicked and not held
	public void mouseClicked() {
		if (gui.addNodeButton.checkOverlap()) {
			createNode();
		}
		// Checks for left mouse click
		if (launcher.mouseButton == PApplet.LEFT) {
			for (Node n : nodes) {
				if (n.nodeButton.checkOverlap())
					selectedNode = n;
			}
		}
		// Checks for right mouse click
		if (launcher.mouseButton == PApplet.RIGHT) {
			for (Node n : nodes) {
				if (n.nodeButton.checkOverlap()) {
					if (selectedNode != n) {
						boolean contains = false;
						for (Connection c : selectedNode.connections) {
							if (c.node2 == n)
								contains = true;
						}
						if (!contains) {
							Connection connection = new Connection(launcher, selectedNode, n, selectedNode.color);
							selectedNode.connections.add(connection);
						}
					}
					if (selectedNode == n) {
						/*	TODO fix removing nodes
						 *	nodes are not being set to null through
						 *	the connections of other nodes
						 */
						nodes.remove(selectedNode);
						if (!nodes.isEmpty())
							selectedNode = nodes.get(0);
						checkNodeConnections();
						break;
					}
				}
			}
		}
	}
	
	private void checkNodeConnections() {
		for (Node n : nodes) {
			n.checkConnections();
		}
	}
	
	// Will run if the mouse is clicked and held
	public void mousePressed() {
		if (launcher.mouseButton == PApplet.LEFT) {
			for (Node n : nodes) {
				if (n.nodeButton.checkOverlap()) {
					selectedNode = n;
					n.mousePressed();
				}
			}
		}
	}
	
	// Will run if the mouse is dragged across the screen
	public void mouseDragged() {
		for (Node n : nodes) {
			n.mouseDragged();
		}
	}
	
	// Will run if the mouse is released after being held
	public void mouseReleased() {
		for (Node n : nodes) {
			n.mouseReleased();
		}
	}
}
