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
        checkEdgeCollision();
    }

    //using newton's second law: F = m*a -> a = F/m
    public void applyForce(PVector force){
        PVector a = PVector.div(force, mass);
        acceleration.add(a);
    }

    public void move(){
        velocity.add(acceleration);
        position.add(velocity);
        acceleration = new PVector();
    }

    public void show(){
        //applet.rectMode(3);//center rect mode
        applet.rect(position.x, position.y, size, size);
    } 

    public void checkEdgeCollision(){
        //reversing x-component of velocity vector if there is edge collision to the right or left
        //same goes for y-component with up/down edge collision
        //the position is set to be size/2 away from edge to avoid getting stuck on the wrong
        //side of the edge
        if (position.x > applet.width-size/2){
            velocity.x *= -1;
            position.x = applet.width-size/2;
        }
        if (position.x < size/2){
            velocity.x *= -1;
            position.x = size/2;
        }
        if (position.y > applet.height-size/2){
            velocity.y *= -1;
            position.y = applet.height-size/2;
        }
        if (position.y < size/2){
            velocity.y *= -1;
            position.y = size/2;
        }
    }
}