package CannonGame;

import processing.core.*;
import processing.awt.*;

public class CannonGame extends PApplet{
	public static CannonGame applet;
	SquareBall b;
	PVector wind;
	PVector gravity;
	
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
		b = new SquareBall(new PVector(width/2, height/2), PVector.random2D(),
		  new PVector(25,25), new PVector(25,-25), random(PI * 2), PI/20, 1f, 25 );
		wind = new PVector(0.05f, 0);
		gravity = new PVector(0, 0.98f);
	}
	
	//called every frame
	public void draw() {
		background(180);
		b.update();
		b.applyForce(wind);
		b.applyForce(gravity);
	}
}
