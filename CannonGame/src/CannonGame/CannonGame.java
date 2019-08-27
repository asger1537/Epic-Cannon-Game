package CannonGame;

import processing.core.*;

public class CannonGame extends PApplet{
	public static CannonGame applet;
	SquareBall b;
	public static void main(String[] args) {
		PApplet.main("CannonGame.CannonGame");
	}

	//only the window size needs to be set here
	public void settings() {
		//for testing purposes the size can be used, but otherwise use fullscreen
		size(800, 800);
		applet = this;
		//fullScreen();
	}
	
	//initialize things
	public void setup() {
		b = new SquareBall(new PVector(width/2, height/2), PVector.random2D(), applet.random(PI * 2), 4, 1, 50);
	}
	
	//called every frame
	public void draw() {
		background(180);
		b.update();
	}
}
