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
        move();
        updateCorners();
        applyAirResistance();
        checkEdgeCollision();
        updateCorners();
        show();
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
        applet.beginShape();
        for (PVector c : corners){
            applet.vertex(c.x, c.y);
        }
        applet.endShape(2);
    } 
		//todo change to use Terrain class
    public void checkEdgeCollision(){
        int xCollisions = 0;
        int yCollisions = 0;

        float velocityMultiplier = 0.5f;
        for (PVector c : corners){
            if (c.x > applet.width){
                applet.println("right collision");
                xCollisions++;
                if (xCollisions == 1){
                    position.x -= c.x-applet.width;
                    velocity.x *= -velocityMultiplier;
                    aVelocity *= -0.8;
                }
            }
            if (c.x < 0){
                xCollisions++;
                applet.println("left collision");
                if (xCollisions == 1){
                    position.x -= c.x;
                    velocity.x *= -velocityMultiplier;
                    aVelocity *= -0.3;
                }
            }
            if (c.y >= applet.height){
                yCollisions++;
                applet.println("bottom collision");
                if (yCollisions == 1){
                    position.y -= c.y-applet.width;
                    velocity.y *= -velocityMultiplier;
                    aVelocity +=(c.x-position.x)/50f;
                    aVelocity *= -0.3;
                    velocity.x *= 0.5;
                }
            }
            if (c.y < 0){
                applet.println("top collision");
                if (yCollisions == 1){
                    yCollisions++;
                    position.y -= c.y;
                    velocity.y *= -velocityMultiplier;
                    aVelocity *= -1;
                    velocity.x *= 0.5;
                }
            }

            applet.println("x", xCollision);
            applet.println("y", yCollision);
            applet.println(aVelocity);
            //applet.println(velocity.x, velocity.y);
        }
    }
}