package CannonGame;

import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class Cannon {
	PVector barrel;

	PVector position;
	float barrelAngleVelocity;
	float barrelAngle;
	float movementSpeed;
	float barrelLength;
	float barrelWidth;
	float wheelRadius;
	float wheelCount;
	float a;
	ArrayList<SquareBall> squareBalls;
	float tankAngle;

	Cannon(PVector position) {
		this.wheelCount = 5;
		this.wheelRadius = 30f;
		this.barrelWidth = 20f;
		this.position = position;
		this.barrelAngle = barrelAngle;
		this.barrelAngleVelocity = -PConstants.PI / 180;
		this.movementSpeed = 5f;
		this.barrelLength = 100f;
		this.barrel = new PVector(100, 0);

		squareBalls = new ArrayList();
	}

	void update() {
		move();
		show();
		for (int i = 0; i < squareBalls.size(); i++){
			SquareBall sb = squareBalls.get(i);
			sb.update();
			sb.applyForce(applet.gravity);
		}
	}

	// moving the tank and barrel
	void move() {

	}

	// drawing the tank - i.e the barrel, wheels and maybe some extra
	void show() {
		barrelAngle += barrelAngleVelocity;
		applet.pushMatrix();
		
		// make tank small
		applet.scale(0.33f);
		//Translates the center of the grid to position of tank
		applet.translate(position.x, position.y - 30);
		// Rotates tank in its entirety including barrel
		applet.rotate(barrelAngle);
		applet.line(0, 0, barrel.x, barrel.y);
		
		//rotates PVector describing direction of barrel
		barrel.rotate(barrelAngleVelocity);
		// for-loop draws wheels of tank
		for (int i = 0; i < wheelCount; i++) {
			applet.fill(55);
			applet.ellipse(wheelRadius * 2 * i - 4 * wheelRadius, 55 + wheelRadius, wheelRadius * 2, wheelRadius * 2);
		}
		//The vertices draw the shape of the tank
		applet.fill(125);
		applet.beginShape();
		applet.vertex(-5 * wheelRadius, 55 + wheelRadius);
		applet.vertex(5 * wheelRadius, 55 + wheelRadius);
		applet.vertex(150, 55);
		applet.vertex(100, 5);
		applet.vertex(50, 5);
		applet.vertex(50, -15);
		applet.vertex(-50, -15);
		applet.vertex(-50, 5);
		applet.vertex(-100, 5);
		applet.vertex(-150, 55);
		applet.endShape(2);
		applet.popMatrix();
		// printing for testing-purposes
		//applet.println(angle);
		//applet.println(barrel);
	}

	void shoot() {
		applet.println("shoot");
		squareBalls.add(new SquareBall(PVector.add(position, barrel), 10,barrelAngle, 0, 1, 20));
	}
}
