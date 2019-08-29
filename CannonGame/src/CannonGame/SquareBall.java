package CannonGame;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class SquareBall {
    
    PVector position, velocity, acceleration;
    float angle, aVelocity, aAcceleration;
    float size, mass;
    
    SquareBall(PVector position, PVector velocity, float angle, float aVelocity, 
    float mass, float size){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new PVector();

        this.angle = angle;
        this.aVelocity = aVelocity;
        this.aAcceleration = 0;

        this.size = size;
        this.mass = mass;
    }

    void update(){
        show();
        move();
        applyAirResistance();
        checkEdgeCollision();
    }

    //using newton's second law: F = m*a -> a = F/m
    public void applyForce(PVector force){
        PVector a = PVector.div(force, mass);
        acceleration.add(a);
        aAcceleration += acceleration.mag()/10f;
    }

    public void move(){
        velocity.add(acceleration);
        position.add(velocity);
        acceleration = new PVector();

        aVelocity += aAcceleration;
        angle += aVelocity;
        aAcceleration = 0;
    }

    void applyAirResistance(){
        this.applyForce(PVector.div(velocity, -200));
    }

    public void show(){
        //applet.rectMode(3);//center rect mode
        applet.translate(position.x, position.y);
        applet.rotate(angle);
        applet.rectMode(3);
        applet.rect(0, 0, size, size);

    } 
		//todo change to use Terrain class
    public void checkEdgeCollision(){
        //reversing x-component of velocity vector if there is edge collision to the right or left
        //same goes for y-component with up/down edge collision
        //the position is set to be size/2 away from edge to avoid getting stuck on the wrong
        //side of the edge
        if (position.x > applet.width-size/2){
            velocity.x *= -.7;
            position.x = applet.width-size/2;
        }
        if (position.x < size/2){
            velocity.x *= -.7;
            position.x = size/2;
        }
        if (position.y > applet.height-size/2){
            velocity.y *= -.7;
            position.y = applet.height-size/2;
        }
        if (position.y < size/2){
            velocity.y *= -.7;
            position.y = size/2;
        }
    }
}