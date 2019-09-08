package CannonGame;

import java.io.Console;
import java.util.ArrayList;

import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class Tank {
	PVector position, direction, collisionPoint1, collisionPoint2;
	float w, h;
	float barrelLength;
	PVector barrelPosition;
	PVector barrelDirection;
	float moveSpeed;
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
		moveSpeed = 5f;
		squareBalls = new ArrayList<>();
	}

	void update() {
		show();
		updateCollisionPoints();
		for (int i = 0; i < squareBalls.size(); i++){
            SquareBall sb = squareBalls.get(i);
            sb.update();
            sb.applyForce(applet.gravity);
        }
	}

	void move(int d) {
		applet.println(getTankAngle());
		position.x += moveSpeed*d;
		position.y = applet.terrain.heightmap[(int)position.x]-applet.sin(getTankAngle())*16f;	
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
		applet.rotate(getTankAngle());
		applet.image(applet.tankImg, 0, 0);
		applet.popMatrix();
	}

	void shoot() {
		applet.println("shoot");
		squareBalls.add(new SquareBall(PVector.add(position, PVector.mult(barrelDirection, barrelLength)), 10,
				barrelDirection.heading(), 0, 1, 20));
	}

	float getTankAngle()
	{
		float tankSlopeMax = -1000;
		
		float width = Math.abs(collisionPoint1.x-collisionPoint2.x);

		//terrain x-coord for tank left most collisionpoint
		int x1 = (int) Math.floor(Math.min(collisionPoint1.x, collisionPoint2.x));
		//terrain y-coord for tank left most collisionpoint
		int y1 =	applet.terrain.heightmap[x1];

		for(int i = 0; i < width; ++i)
		{
			// terrain x-coord for increments of i
			int x2 = x1+i;
			
			// terrain y-coord for increments of i
			int y2 = applet.terrain.heightmap[x2];
			
			//cast i to float to avoid rounding
			float tankSlope =  (y2-y1)/(float)i;

			// ensures max slope is highest slope
			if(tankSlope > tankSlopeMax){tankSlopeMax = tankSlope;}
		}
		PVector slopeVector = new PVector(1,tankSlopeMax);
		// gets the tank angle
		float tankAngle = (float)Math.atan2(slopeVector.y,slopeVector.x);
		return tankAngle;
	}	


}
