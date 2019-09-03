package CannonGame;

import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class Cannon {
	PVector position;
	PVector barrel;

	float angleVelocity;
	float angle;
	float movementSpeed;
	float barrelLength;
	float barrelWidth;
	float wheelRadius;
	float wheelCount;
	float a;

	Cannon(PVector position) {
		this.wheelCount = 5;
		this.wheelRadius = 30f;
		this.barrelWidth = 20f;
		this.position = position;
		this.angle = a;
		this.angleVelocity = 0.1f;
		this.movementSpeed = 5f;
		this.barrelLength = 100f;
		this.barrel = new PVector(100, 0);
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
		a++;
		angle += PConstants.PI / 180;
		applet.pushMatrix();
		applet.translate(position.x, position.y - 30);
		// applet.rotate(-PConstants.PI/((180)/a));
		applet.pushMatrix();
		barrel.rotate(angle);
		applet.beginShape();
		applet.vertex();
		applet.endShape(2);
		applet.rect(barrelLength / 2, 0, barrelLength, barrelWidth);
		applet.popMatrix();

		for (int i = 0; i < wheelCount; i++) {
			applet.ellipse(wheelRadius * 2 * i - 4 * wheelRadius, 55 + wheelRadius, wheelRadius * 2, wheelRadius * 2);
		}
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
		applet.rect(0, -10, 100, 30);

		// println((atan(barrel.x/barrel.y))/PI*180);
		applet.popMatrix();
		applet.println(angle);
		applet.println(barrel);
	}

	void shoot() {

	}

}
