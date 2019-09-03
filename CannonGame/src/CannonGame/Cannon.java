package CannonGame;

import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;


public class Cannon {
	PVector position;	
	float angleVelocity;
	float angle;
	float movementSpeed;
	float barrelLength;

	float barrelWidth;
	float wheelRadius;
	float wheelCount;
	float a;

	ArrayList <SquareBall> squareBalls;
	
	Cannon(PVector position){
		this.wheelCount = 5;
		this.wheelRadius = 30f;
		this.barrelWidth = 20f;
		this.position = position;
		this.angle = -PConstants.PI;
		this.angleVelocity = 0.1f;
		this.movementSpeed = 5f;
		this.barrelLength = 100f;

		squareBalls = new ArrayList();
	}
	
	void update() {
		move();
		show();
	}
	
	//moving the cannon and barrel
	void move() {
		
	}
	
	//drawing the cannon - i.e the barrel, wheels and maybe some extra
	void show() {
		a++;
		applet.pushMatrix();
  applet.translate(position.x,position.y-30);  
  //applet.rotate(angle/((180)/a));
  applet.pushMatrix();
  applet.rotate(angle/(180/a));
  applet.rectMode(3);
  applet.rect(barrelLength/2, 0, barrelLength, barrelWidth);
  applet.popMatrix();
  
  for(int i=0; i<wheelCount; i++) {
	applet.ellipse(wheelRadius*2*i-4*wheelRadius, 55+wheelRadius, wheelRadius*2, wheelRadius*2);  
	}
	applet.beginShape();
	applet.vertex(-5*wheelRadius,55+wheelRadius);
	applet.vertex(5*wheelRadius,55+wheelRadius);
	applet.vertex(150,55);
	applet.vertex(100,5);
	applet.vertex(50,5);
	applet.vertex(50,-15);
	applet.vertex(-50,-15);
	applet.vertex(-50,5);

	applet.vertex(-100,5);
	applet.vertex(-150,55);
	applet.endShape(2);
  applet.rect(0,-10,100,30);
  
 // println((atan(barrel.x/barrel.y))/PI*180);
  applet.popMatrix();
	}
	
	void shoot() {
		squareBalls.add(new SquareBall(position, velocity, angle, aVelocity, 
		mass, size));
	}
	
}
