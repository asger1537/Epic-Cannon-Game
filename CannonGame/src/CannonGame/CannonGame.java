package CannonGame;

import processing.core.*;
//import processing.awt.*;


public class CannonGame extends PApplet{
	public static CannonGame applet;
	SquareBall b;
	Cannon c;
	PVector wind;
	PVector gravity;
	Terrain terrain;
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
<<<<<<< HEAD
		frameRate(10);
=======
		c = new Cannon(new PVector(width/2f,height/2f));
>>>>>>> 28100d2c3625fa102f13a095aba944a985cfa01d
		b = new SquareBall(new PVector(width/2, height/2), PVector.random2D(), random(PI * 2), PI/20, 1f, 25 );
		wind = new PVector(0.05f, 0);
		gravity = new PVector(0, 0.98f);
		terrain = new Terrain();
		terrain.generate();
	}
	
	//called every frame
	public void draw() {
		background(180);
		terrain.displayTerrain();
		c.update();
		b.update();
		//b.applyForce(wind);
		b.applyForce(gravity);
	}
}
