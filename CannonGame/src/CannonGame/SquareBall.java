package CannonGame;
import processing.core.PConstants;
import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class SquareBall {
    
    PVector position, velocity, acceleration, squareDraw_1, squareDraw_2, squareCorner_1, squareCorner_2, squareCorner_3, squareCorner_4;
    PVector[] corners;
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
        this.mass = mass;
        this.size = size;

        corners = new PVector[4];
        updateCorners();
    }

    void update(){    
        show();
        move();
        updateCorners();
        applyAirResistance();
        checkEdgeCollision();
    }

    //using newton's second law: F = m*a -> a = F/m
    public void applyForce(PVector force){
        PVector a = PVector.div(force, mass);
        acceleration.add(a);
        //aAcceleration += acceleration.mag()/10f;
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
        //applies a portion of the velocity as a force in the opposite direction
        this.applyForce(PVector.div(velocity, -200));
    }

    void updateCorners(){
        //applet.println("Updating corners");
        PVector v1 = new PVector(-size/2, size/2).rotate(angle);
        PVector v2 = new PVector(size/2, size/2).rotate(angle);

        corners[0] = PVector.add(position, v1);
        corners[1] = PVector.add(position, v2);
        corners[2] = PVector.add(position, v1.mult(-1));
        corners[3] = PVector.add(position, v2.mult(-1));
    }

    public void show(){
        //drawing lines clockwise
        applet.line(corners[0].x, corners[0].y, corners[1].x, corners[1].y);
        applet.line(corners[1].x, corners[1].y, corners[2].x, corners[2].y);
        applet.line(corners[2].x, corners[2].y, corners[3].x, corners[3].y);
        applet.line(corners[3].x, corners[3].y, corners[0].x, corners[0].y);
    } 

    public void checkEdgeCollision(){
        //reversing x-component of velocity vector if there is edge collision to the right or left
        //same goes for y-component with up/down edge collision
        //the position is set to be size/2 away from edge to avoid getting stuck on the wrong
        //side of the edge
        boolean xCollision = false;
        boolean yCollision = false;
        for (PVector c : corners){
            if (c.x > applet.width && !xCollision){
                xCollision = true;
                position.x -= c.x-applet.width;
                velocity.x *= -0.7;
                aVelocity *= -0.8;
            }
            if (c.x < 0 && !xCollision){
                xCollision = true;
                position.x -= c.x;
                position.x *= -0.7;
                aVelocity *= -0.8;
            }
            if (c.y > applet.height && !yCollision){
                yCollision = true;
                position.y -= c.y-applet.width;
                velocity.y *= -0.7;
                aVelocity *= -0.8;
            }
            if (c.y < 0 && !yCollision){
                yCollision = true;
                position.y -= c.y;
                position.y *= -1;
                aVelocity *= -0.8;
            }
        }
    }
}