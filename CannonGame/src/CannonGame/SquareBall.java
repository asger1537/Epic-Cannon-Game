package CannonGame;
import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class SquareBall {
    
    PVector position, velocity, acceleration, squareDraw_1, squareDraw_2, squareCorner_1, squareCorner_2, squareCorner_3, squareCorner_4;
    float angle, aVelocity, aAcceleration;
    float size, mass;
    
    SquareBall(PVector position, PVector velocity, PVector squareDraw_1, PVector squareDraw_2, float angle, float aVelocity, 
    float mass, float size){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new PVector();

        this.angle = angle;
        this.aVelocity = aVelocity;
        this.aAcceleration = 0;

        this.mass = mass;
        this.squareDraw_1 = squareDraw_1;
        this.squareDraw_2 = squareDraw_2;
        
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

      //  applet.rectMode(3);//center rect mode
        applet.translate(position.x, position.y);
        applet.beginShape();
        applet.vertex(squareDraw_1.x,squareDraw_1.y);
        applet.vertex(squareDraw_2.x,squareDraw_2.y);
        applet.vertex(-squareDraw_1.x,-squareDraw_1.y);
        applet.vertex(-squareDraw_2.x,-squareDraw_2.y);
        applet.endShape(2);
        squareDraw_1.rotate(PConstants.PI/180);
        squareDraw_2.rotate(PConstants.PI/180);
        //applet.rect(0, 0, size, size);

    } 

    public void checkEdgeCollision(){
        //reversing x-component of velocity vector if there is edge collision to the right or left
        //same goes for y-component with up/down edge collision
        //the position is set to be size/2 away from edge to avoid getting stuck on the wrong
        //side of the edge
        if (position.x > applet.width-size){
            velocity.x *= -.7;
            position.x = applet.width-size;
        }
        if (position.x < size){
            velocity.x *= -.7;
            position.x = size;
        }
        if (position.y > applet.height-size){
            velocity.y *= -.7;
            position.y = applet.height-size;
        }
        if (position.y < size){
            velocity.y *= -.7;
            position.y = size;
        }
    }
}