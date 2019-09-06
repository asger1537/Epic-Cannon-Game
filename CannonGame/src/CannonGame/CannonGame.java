package CannonGame;

import processing.core.*;

public class CannonGame extends PApplet{
	public static CannonGame applet;
	SquareBall b;
	public Tank t;
	PVector wind;
	PVector gravity;
	Button[] buttons;
	int scene;
	Terrain terrain;
	PImage tankImg;
	public static void main(String[] args) {
		PApplet.main("CannonGame.CannonGame");
	}

	//only the window size needs to be set here
	public void settings() {
		//for testing purposes the size can be used, but otherwise use fullscreen
		size(1025, 800);
		applet = this;
		//fullScreen();
	}
	
	//initialize things
	public void setup() {
		frameRate(60);
		t = new Tank(new PVector(width/2f,height/2f));
		b = new SquareBall(new PVector(width/2, height/2), 5, random(PI * 2), PI/20, 1f, 25 );
		wind = new PVector(0.05f, 0);
		gravity = new PVector(0, 0.98f);
		buttons = new Button[5];
		scene = 0;
		terrain = new Terrain();
		terrain.generate();
		tankImg = loadImage("https://i.imgur.com/FeiiJ3W.png");
		println(tankImg);
	}
	
	//called every frame
	public void draw() {
		//System.out.println(frameRate);
		background(202, 236, 244);
		t.update();
		b.update();
		//b.applyForce(wind);
		b.applyForce(gravity);
		terrain.displayTerrain();
		//image(tankImg, 300, 100);
	}

	@Override
	public void keyPressed(){
		Input.keyPressed();		
	}
	@Override
	public void mouseClicked(){
		Input.mouseClicked();
    }
}
