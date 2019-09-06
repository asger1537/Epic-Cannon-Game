package CannonGame;

import java.util.ArrayList;

import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class Tank {
	PVector position, direction, collisionPoint1, collisionPoint2;
	float w, h;
	float barrelLength;
	PVector barrelPosition;
	PVector barrelDirection;
	PVector velocity;
	ArrayList <SquareBall> squareBalls;

	Tank(PVector position) {
		this.position = position;
		direction = new PVector(1, 0);
		w = 100;
		h = 50;
		collisionPoint1 = new PVector();// the left collision point
		collisionPoint2 = new PVector();// the right collision point
		barrelLength = 80;
		barrelPosition = new PVector(barrelLength, 0);
		barrelDirection = new PVector(1, 0);
		velocity = new PVector();
		squareBalls = new ArrayList<>();
	}

	void update() {
		show();
		move();
		updateCollisionPoints();
		for (int i = 0; i < squareBalls.size(); i++){
            SquareBall sb = squareBalls.get(i);
            sb.update();
            sb.applyForce(applet.gravity);
        }
	}

	void move() {
		position.add(velocity);
	}

	void updateCollisionPoints() {
		collisionPoint1 = PVector.add(PVector.add(position, new PVector(-direction.y, direction.x).mult(15)),
				PVector.mult(direction, 40));
		collisionPoint2 = PVector.add(PVector.add(position, new PVector(-direction.y, direction.x).mult(15)),
				PVector.mult(direction, -40));
	}

	void show() {
		float yOffSet = 10;
		PVector barrelBase = new PVector(position.x, position.y - yOffSet);
		float barrelRadius = 5;

		applet.fill(0);
		// drawing the barrel
		applet.beginShape();
		PVector v1 = PVector.add(barrelBase, new PVector(-barrelDirection.y, barrelDirection.x).mult(barrelRadius));
		PVector v2 = PVector.add(barrelBase, new PVector(barrelDirection.y, -barrelDirection.x).mult(barrelRadius));
		PVector v3 = PVector.add(v2, PVector.mult(barrelDirection, barrelLength));
		PVector v4 = PVector.add(v1, PVector.mult(barrelDirection, barrelLength));
		applet.vertex(v1.x, v1.y);
		applet.vertex(v2.x, v2.y);
		applet.vertex(v3.x, v3.y);
		applet.vertex(v4.x, v4.y);
		applet.endShape(2);

		// drawing the tank body
		applet.imageMode(3);
		applet.pushMatrix();
		applet.translate(position.x, position.y);
		applet.rotate(direction.heading());
		applet.image(applet.tankImg, 0, 0);
		applet.popMatrix();
	}

	void shoot() {
		applet.println("shoot");
		squareBalls.add(new SquareBall(PVector.add(position, PVector.mult(barrelDirection, barrelLength)), 10,
				barrelDirection.heading(), 0, 1, 20));
	}

}