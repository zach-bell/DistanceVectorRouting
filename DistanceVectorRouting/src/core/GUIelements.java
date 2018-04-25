package core;

import java.util.ArrayList;

import core.base.Button;
import processing.core.PApplet;
import processing.core.PVector;

public class GUIelements {
	
	private PApplet launcher;
	public Button addNodeButton;
	
	public ArrayList<Button> buttons;
	
	public GUIelements(PApplet launcher) {
		this.launcher = launcher;
		buttons = new ArrayList<Button>();
	}
	
	public void setup() {
		addNodeButton = new Button(launcher, "Add Node", new PVector(launcher.width - 80, launcher.height - 30),
													new PVector(70, 20));
		createButton(addNodeButton);
	}
	
	public void draw() {
		for (Button b : buttons) {
			if (!b.noDraw)
				b.draw();
		}
	// Initial text pre-settings
		launcher.textAlign(PApplet.LEFT);
		launcher.textSize(13);
		launcher.fill(255,255,255);
	// Mouse display
		launcher.text("mouseX: "+launcher.mouseX,2, 13);
		launcher.text("mouseY: "+launcher.mouseY,95, 13);
		
	// Instructions
		launcher.text("Left click to select node / Drag node", 220, 13);
		launcher.text("Right click to make connection / Delete node", 480, 13);
	}
	
	public void createButton(Button button) {
		buttons.add(button);
	}
}
