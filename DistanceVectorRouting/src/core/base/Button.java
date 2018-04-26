package core.base;

import processing.core.PApplet;
import processing.core.PVector;

public class Button {
	
	public PVector position;
	public PVector size;
	public boolean noDraw = false;
	public boolean noFill = false;
	public String text;
	
	private PApplet launcher;
	private int fillColor;
	private int textColor;
	
	public Button(PApplet launcher, String text, PVector position, PVector size) {
		this.launcher = launcher;
		this.position = position;
		this.size = size;
		this.text = text;
		
		fillColor = launcher.color(240, 240, 240);
		textColor = launcher.color(0, 0, 0);
	}
	public Button(PApplet launcher, String text, PVector position, PVector size, int fillColor, int textColor) {
		this.launcher = launcher;
		this.position = position;
		this.size = size;
		this.text = text;
		this.fillColor = fillColor;
		this.textColor = textColor;
	}
	
	public void draw() {
		launcher.rectMode(PApplet.CORNER);
		launcher.fill(fillColor);
		if (noFill) {
			launcher.noFill();
			launcher.stroke(fillColor);
		}
		launcher.rect(position.x, position.y, size.x, size.y);
		if (noFill) launcher.noStroke();
		
		launcher.textAlign(PApplet.LEFT);
		launcher.fill(textColor);
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
