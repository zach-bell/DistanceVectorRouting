package core.table;

import core.base.Node;
import processing.core.PApplet;

public class TextTableHolders {
	
	private Node destination, outgoing;
	private PApplet tableDisplay;
	private int delay, x, n, textSize;
	
	public TextTableHolders(PApplet tableDisplay, Node destination, int delay, Node outgoing, int x, int n, int textSize) {
		this.tableDisplay = tableDisplay;
		this.destination = destination;
		this.delay = delay;
		this.outgoing = outgoing;
		this.x = x;
		this.n = n;
		this.textSize = textSize;
	}

	public void draw() {
		tableDisplay.text(destination.name, x, (n * textSize) + 2);
		tableDisplay.text("|", x + 40, (n * textSize) + 2);
		tableDisplay.text(delay, x + 45, (n * textSize) + 2);
		tableDisplay.text("|", x + 90, (n * textSize) + 2);
		tableDisplay.text(outgoing.name, x + 95, (n * textSize) + 2);
	}
}
