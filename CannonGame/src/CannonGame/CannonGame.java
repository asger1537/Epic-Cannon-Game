package CannonGame;

import processing.core.PApplet;

public class CannonGame extends PApplet{
	public static CannonGame applet;
	
	//only the window size needs to be set here
	public void settings() {
		//for testing purposes the size can be used, but otherwise use fullscreen
		size(800, 800);
		//fullScreen();
	}
	
	//initialize things
	public void setup() {
		b = new SquareBall(new PVector(width/2, height/2), PVector.random2D(), 1, 50);
	}
	
	//called every frame
	public void draw() {
		
	}
}
