package CannonGame;

import processing.core.PVector;
import static CannonGame.CannonGame.applet;

public class SquareBall {

    PVector position, pPosition, velocity, acceleration;
    PVector[] corners;
    float angle, aVelocity, aAcceleration;
    float size, mass;

    SquareBall(PVector position, float speed, float angle, float aVelocity, float mass, float size) {
        this.position = position;
        this.pPosition = new PVector();
        this.velocity = new PVector(1, 0).rotate(angle).mult(speed);
        this.acceleration = new PVector();

        this.angle = angle;
        this.aVelocity = aVelocity;
        this.aAcceleration = 0;
        this.mass = mass;
        this.size = size;

        corners = new PVector[4];
        updateCorners();
    }

    void update() {
        move();
        updateCorners();
        applyAirResistance();
        checkEdgeCollision();
        checkTerrainCollision();
        updateCorners();
        pPosition = position;
        show();

    }

    // using newton's second law: F = m*a -> a = F/m
    public void applyForce(PVector force) {
        PVector a = PVector.div(force, mass);
        acceleration.add(a);
        // aAcceleration += acceleration.mag()/10f;
    }

    public void move() {
        if (velocity.mag() <= 0.05f)
            return;
        velocity.add(acceleration);
        position.add(velocity);
        acceleration = new PVector();

        aVelocity += aAcceleration;
        angle += aVelocity;
        aAcceleration = 0;
    }

    void applyAirResistance() {
        // Applies a portion of the velocity as a force in the opposite direction
        this.applyForce(PVector.div(velocity, -200));
    }

    void updateCorners() {
        // applet.println("Updating corners");
        PVector v1 = new PVector(-size / 2, size / 2).rotate(angle);
        PVector v2 = new PVector(size / 2, size / 2).rotate(angle);

        corners[0] = PVector.add(position, v1);
        corners[1] = PVector.add(position, v2);
        corners[2] = PVector.add(position, v1.mult(-1));
        corners[3] = PVector.add(position, v2.mult(-1));
    }

    public void show() {
        applet.beginShape();
        for (PVector c : corners) {
            applet.vertex(c.x, c.y);
        }
        applet.endShape(2);
    }

    public void checkTerrainCollision() {
        // no need the check collision if the squareBall isn't moving
        if (velocity.mag() == 0)
            return;

        int terrainCollisions = 0;
        float velocityMultiplier = 0.5f;

        for (PVector c : corners) {
            // If the corner is below the terrain line
            if (c.x > 0 && c.x < applet.width && c.y >= applet.terrain.heightmap[(int) Math.round(c.x)]) {
                terrainCollisions++;
                // If the squareBall is moving fast more than one corner might collide
                // In that case only one of the corners need to do the collision physics
                if (terrainCollisions == 1) {
                    // Moving the squareBall up by the y-distance it overstepped the terrain
                    position.y -= c.y - applet.terrain.heightmap[(int) c.x];
                    // Reflection
                    float terrainHeading = new PVector(3,
                            applet.terrain.heightmap[(int) c.x + 1] - applet.terrain.heightmap[(int) c.x - 1])
                                    .heading();

                    velocity.rotate(-terrainHeading);
                    velocity.y *= -1;
                    velocity.rotate(terrainHeading);

                    // Making sure the squareBall doesn't balance on 1 corner
                    aVelocity += (c.x - position.x) / 50f;
                    // Reversing the rotation direction and losing angularvelocity
                    aVelocity *= -0.5;
                    velocity.mult(velocityMultiplier);
                    // Sets velocity to 0 if the velocity is low to prevent jitter
                    if (velocity.mag() < 0.2f) {
                        velocity.setMag(0);
                        aVelocity = 0;
                    }
                }
            }
        }
    }

    public void checkEdgeCollision() {
        int xCollisions = 0;
        int yCollisions = 0;

        // makes the ball decellerate on collision
        float velocityMultiplier = 0.5f;
        // checks collision with window borders for each corner
        for (PVector c : corners) {
            // collision with right edge of the window
            if (c.x > applet.width) {
                xCollisions++;
                if (xCollisions == 1) {
                    position.x -= c.x - applet.width;
                    velocity.x *= -velocityMultiplier;
                    aVelocity *= -0.8;
                }
            }
            // collision with right edge of the window
            if (c.x < 0) {
                xCollisions++;
                if (xCollisions == 1) {
                    position.x -= c.x;
                    velocity.x *= -velocityMultiplier;
                    aVelocity *= -0.3;
                }
            }
            // collision with the bottom of the window
            if (c.y >= applet.height) {
                yCollisions++;
                if (yCollisions == 1) {
                    position.y -= c.y - applet.width;
                    velocity.y *= -velocityMultiplier;
                    aVelocity += (c.x - position.x) / 50f;
                    aVelocity *= -0.3;
                    velocity.x *= 0.5;
                }
            }
            // collision with the top of the window
            if (c.y < 0) {
                if (yCollisions == 1) {
                    yCollisions++;
                    position.y -= c.y;
                    velocity.y *= -velocityMultiplier;
                    aVelocity *= -1;
                    velocity.x *= 0.5;
                }
            }
        }
    }
}