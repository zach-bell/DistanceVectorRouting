package applet;

import core.Main;
import processing.core.PApplet;

public class Launcher extends PApplet {
	
	private Main main;
	
	public static void main(String args[]) {
		PApplet.main("applet.Launcher");
	}
	
	public void settings() {
		size(800, 600);
	}
	
	public void setup() {
		main = new Main(this);
		main.setup();
	}
	
	public void draw() {
		background(0);
		main.draw();
	}
	
	public void mouseClicked() {
		main.mouseClicked();
	}
	
	public void mousePressed() {
		main.mousePressed();
	}
	
	public void mouseDragged() {
		main.mouseDragged();
	}
	
	public void mouseReleased() {
		main.mouseReleased();
	}
}
