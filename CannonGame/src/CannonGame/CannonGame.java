package CannonGame;

import processing.core.*;
//import processing.awt.*;


public class CannonGame extends PApplet{
	public static CannonGame applet;
	SquareBall b;
	Cannon c;
	PVector wind;
	PVector gravity;
	Button[] buttons;
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
		frameRate(60);
		c = new Cannon(new PVector(width/2f,height/2f));
		b = new SquareBall(new PVector(width/2, height/2), 5, random(PI * 2), PI/20, 1f, 25 );
		wind = new PVector(0.05f, 0);
		gravity = new PVector(0, 0.98f);
		buttons = new Button[5];
		terrain = new Terrain();
		//terrain.generate();
		//Input.test();
	}
	
	//called every frame
	public void draw() {
		background(180);
		//terrain.displayTerrain();
		c.update();
	}

	public void keyPressed(){
		Input.keyPressed();
	}

	public void mouseClicked(){
		Input.mouseClicked();
    }
}
