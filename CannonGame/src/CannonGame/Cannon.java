package CannonGame;

import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class Cannon {
	PVector barrel;

	PVector position;
	float angleVelocity;
	float angle;
	float movementSpeed;
	float barrelLength;
	float barrelWidth;
	float wheelRadius;
	float wheelCount;
	float a;
	ArrayList<SquareBall> squareBalls;

	Cannon(PVector position) {
		this.wheelCount = 5;
		this.wheelRadius = 30f;
		this.barrelWidth = 20f;
		this.position = position;
		this.angle = angle;
		this.angleVelocity = -PConstants.PI / 180;
		this.movementSpeed = 5f;
		this.barrelLength = 100f;
		this.barrel = new PVector(100, 0);

		squareBalls = new ArrayList();
	}

	void update() {
		move();
		show();
	}

	// moving the cannon and barrel
	void move() {

	}

	// drawing the cannon - i.e the barrel, wheels and maybe some extra
	void show() {
		angle += angleVelocity;
		applet.pushMatrix();
		
		// make tank small
		applet.scale(0.33f);
		//Translates the center of the grid to position of tank
		applet.translate(position.x, position.y - 30);
		// Rotates tank in its entirety including barrel
		applet.rotate(angle);
		applet.line(0, 0, barrel.x, barrel.y);
		//rotates PVector describing direction of barrel
		barrel.rotate(angleVelocity);
		// for-loop draws wheels of tank
		for (int i = 0; i < wheelCount; i++) {
			applet.ellipse(wheelRadius * 2 * i - 4 * wheelRadius, 55 + wheelRadius, wheelRadius * 2, wheelRadius * 2);
		}
		//The vertices draw the shape of the tank
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
		applet.println(angle);
		applet.println(barrel);
	}

	void shoot() {
		squareBalls.add(new SquareBall(position, velocity, angle, aVelocity, mass, size));
	}

	float getAngleFromTerrain(Terrain terrain)
	{
		//fra min x til tanklængde find maksimal hældning
	}
}
