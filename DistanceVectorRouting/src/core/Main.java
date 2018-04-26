package core;

import java.util.ArrayList;
import applet.Launcher;
import core.base.Connection;
import core.base.Node;
import core.table.TableDisplay;
import processing.core.PApplet;
import processing.core.PVector;
import tools.RandomGen;

public class Main {

	private PApplet launcher;
	private RandomGen gen = new RandomGen();
	private ArrayList<Node> nodes;
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private GUIelements gui;
	private TableDisplay tableDisplay;
	private String[] args = {"DVR Table Display"};
	private Node dummy;
	
	public Node selectedNode;
	
	public Main(Launcher launcher) {
		this.launcher = launcher;
		
		tableDisplay = new TableDisplay(launcher, this);
	}
	// Calls setup which runs after processing's libraries
	public void setup() {
		nodes = new ArrayList<Node>();
		gui = new GUIelements(launcher);
		gui.setup();
		
		// General program
		launcher.getSurface().setTitle("Distance Vector Routing");
		
		// Create the table window
		PApplet.runSketch(args, tableDisplay);
		tableDisplay.noLoop();
		tableDisplay.getSurface().setVisible(false);
		
		// reasons unknown
		dummy = new Node(launcher, "-", new PVector(-100,-100));
	}
	// Draws visual elements on the screen specific to this class
	public void draw() {
		for (Connection c : connections) {
			c.draw();
		}
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
		Node temp = new Node(launcher, dummy, gen.nextChar()+gen.nextNumberString(3),
				new PVector((int) (launcher.random(Node.size.x, launcher.width - Node.size.x)),
						(int) (launcher.random(Node.size.y, launcher.height - Node.size.y))));
		selectedNode = temp;
		nodes.add(temp);
		for (Node n : nodes) {
			n.table.destinations.add(selectedNode);
			n.table.delays.add(10000);
			n.table.outgoing.add(dummy);
		}
		selectedNode.newTable(nodes);
		tableDisplay.update();
	}
	// Will run if the mouse is clicked and not held
	public void mouseClicked() {
		if (gui.addNodeButton.checkOverlap()) {
			createNode();
		}
		if (gui.showTableButton.checkOverlap()) {
			toggleTableWindow();
		}
		if (gui.route.checkOverlap()) {
			timeToRoute();
		}
		// Checks for left mouse click
		if (launcher.mouseButton == PApplet.LEFT) {
			for (Node n : nodes) {
				if (n.nodeButton.checkOverlap())
					selectedNode = n;
			}
			tableDisplay.update();
		}
		// Checks for right mouse click
		if (launcher.mouseButton == PApplet.RIGHT) {
			for (Node n : nodes) {
				if (n.nodeButton.checkOverlap()) {
					// if we right click on a different node than selected, make a connection
					if (selectedNode != n) {
						boolean contains = false;
						for (Connection c : connections) {
							if ((c.node1 == n && c.node2 == selectedNode) || (c.node1 == selectedNode && c.node2 == n))
								contains = true;
						}
						if (!contains) {
							Connection connection = new Connection(launcher, selectedNode, n, selectedNode.color);
							selectedNode.connections.add(connection);
							n.connections.add(connection);
							connections.add(connection);
							gui.buttons.add(connection.connectionButton);
							newConnectionForTable(selectedNode, n);
							tableDisplay.update();
						}
					}
					// if we right click on the selected node then delete it
					if (selectedNode == n) {
						int index = nodes.indexOf(selectedNode);
						nodes.remove(selectedNode);
						for (Node node : nodes) {
							node.table.destinations.remove(index);
							node.table.delays.remove(index);
							node.table.outgoing.remove(index);
						}
						checkConnections(selectedNode, n);
						if (!nodes.isEmpty())
							selectedNode = nodes.get(0);
						else
							selectedNode = null;
						tableDisplay.update();
						break;
					}
				} // end of if overlap
			} // end of for nodes
			for (Connection c : connections) {
				if (c.connectionButton.checkOverlap()) {
					gui.buttons.remove(c.connectionButton);
					connections.remove(c);
					break;
				}
			}
		}
	}
	// Check local node connections to stop drawing
	public void checkConnections(Node selected, Node other) {
		ArrayList<Connection> temp = new ArrayList<Connection>();
		PApplet.print("Checking... ");
		for (Connection c : connections) {
			if (c.node1 == selected || c.node1 == other) {
				PApplet.print("found and removing "+selected.name+", ");
				temp.add(c);
			}
			if (c.node2 == other || c.node2 == selected) {
				PApplet.print("found and removing "+selected.name+", ");
				temp.add(c);
			}
		}
		for (Connection c : temp) {
			gui.buttons.remove(c.connectionButton);
			connections.remove(c);
		}
		for (Node n : nodes) {
			for (Connection c : temp) {
				if (n.connections.contains(c)) {
					n.connections.remove(c);
				}
			}
		}
		PApplet.println("Removed them.");
		tableDisplay.update();
	}
	
	public void newConnectionForTable(Node selectedNode, Node other) {
		int index1 = nodes.indexOf(selectedNode);
		int index2 = nodes.indexOf(other);
		selectedNode.table.delays.set(index2,connections.get(connections.size() - 1).mid);
		selectedNode.table.outgoing.set(index2, other);
		other.table.delays.set(index1, connections.get(connections.size() - 1).mid);
		other.table.outgoing.set(index1, selectedNode);
	}
	public void timeToRoute() {
		for (Node n : nodes) {
			for (Connection c : n.connections) {
				int i = 0;
				int cd = c.mid;
				if (c.node1 != n) {
					for (int delay : c.node1.table.delays) {
						if (delay + cd < n.table.delays.get(i)) {
							n.table.delays.set(i, delay + cd);
							n.table.outgoing.set(i, c.node1);
						}
						i++;
					}
				} else {
					for (int delay : c.node2.table.delays) {
						if (delay + cd < n.table.delays.get(i)) {
							n.table.delays.set(i, delay + cd);
							n.table.outgoing.set(i, c.node2);
						}
					}
					i++;
				}
			}
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
			tableDisplay.update();
		}
	}
	// Will run if the mouse is dragged across the screen
	public void mouseDragged() {
		for (Node n : nodes) {
			n.mouseDragged();
		}
		tableDisplay.update();
	}
	// Will run if the mouse is released after being held
	public void mouseReleased() {
		for (Node n : nodes) {
			n.mouseReleased();
		}
		tableDisplay.update();
	}
	// Toggle table window
	public void toggleTableWindow() {
		if (tableDisplay.active) {
			tableDisplay.getSurface().setVisible(false);
			tableDisplay.noLoop();
		}
		if (!tableDisplay.active) {
			tableDisplay.getSurface().setVisible(true);
			tableDisplay.loop();
		}
	}
}
