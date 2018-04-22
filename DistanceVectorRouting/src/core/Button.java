package core;

import processing.core.PApplet;
import processing.core.PVector;

public class Button {
	
	public PVector position;
	public PVector size;
	public boolean noDraw = false;
	
	private PApplet launcher;
	private String text;
	
	public Button(PApplet launcher, String text, PVector position, PVector size) {
		this.launcher = launcher;
		this.position = position;
		this.size = size;
		this.text = text;
	}
	
	public void draw() {
		launcher.rectMode(PApplet.CORNER);
		launcher.fill(240, 240, 240);
		launcher.rect(position.x, position.y, size.x, size.y);
		
		launcher.textAlign(PApplet.LEFT);
		launcher.fill(10, 10, 10);
		launcher.textSize(14);
		launcher.text(text, position.x + 2, position.y + (size.y / 1.2f));
	}

	public boolean checkOverlap() {
		if ((launcher.mouseX > position.x) && (launcher.mouseX < position.x + size.x)) {
			if ((launcher.mouseY > position.y) && (launcher.mouseY < position.y + size.y)) {
				return true;
			}
		}
		return false;
	}
}
