package CannonGame;

import processing.core.PVector;


public class Cannon {
	PVector position;
	
	float angleVelocity;
	float angle;
	float movementSpeed;
	float barrelLength;
	
	Cannon(PVector position){
		this.position = position;
		this.angle = 0f;
		this.angleVelocity = 0.1f;
		this.movementSpeed = 5f;
		this.barrelLength = 50f;
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
		
	}
	
	void shoot() {
	
	}
	
}
