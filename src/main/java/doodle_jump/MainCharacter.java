package doodle_jump;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import utils.ImageUploader;
import game_engine.GameObject;
import physics.Vector;
import doodle_jump.Game; 

public class MainCharacter extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("doodleLeft.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("doodleRight.png");
    private static final Vector JUMP_VECTOR = new Vector(0, -10);
    private static final Vector MOVING_VECTOR = new Vector(0.3, 0);
    private Animation animation;

    public MainCharacter(int x, int y) {
        super(x, y, RIGHT_IMAGE, true, Game.X_LEFT_LIMIT, Game.X_RIGHT_LIMIT);
    }

    public MainCharacter() {
        this(0, 0);
    }

    public void moveLeft() {
        this.subFromBoostVector(MOVING_VECTOR);
    }

    public void moveRight() {
        this.addToBoostVector(MOVING_VECTOR);
    }

    public void jump() {
        this.speedVector.y = 0;
        this.addToSpeedVector(JUMP_VECTOR);
    }

    public void totalStop() {
        //this.setSpeedVector(null);
        this.setBoostVector(null);
    }

    public void startAnimation() {
        this.animation = new Animation();
        this.animation.start();
    }

    public void stopAnimation() {
        if (this.animation != null) {
            this.animation.interrupt();
        }
    }

    private class Animation extends Thread {

        private boolean interrupted = false;

        @Override
        public void run() {
            try {
                while (true) {

                    if (interrupted) {
                        break;
                    }

                    double x = getCoordinateX() + getSpeedVector().getX();
                    double y = getCoordinateY() + getSpeedVector().getY();

                    setLocation((int) x, (int) y);
                    coordinates.setLocation(x, y);

                    speedVector.add(boostVector);

                    if (interrupted) {
                        break;
                    }

                    sleep(10);
                }
            } catch (InterruptedException ex) {
            }
        }

        @Override
        public void interrupt() {
            this.interrupted = true;
        }
    }

    public void move() {
        double x = this.getCoordinateX() + this.getSpeedVector().getX();
        double y = this.getCoordinateY() + this.getSpeedVector().getY();

        this.setLocation((int) x, (int) y);
        this.coordinates.setLocation(x, y);

        this.speedVector.add(this.boostVector);
    }

    /* public void turnRight() {
        this.setBackground(RIGHT_IMAGE);
    }

    public void turnLeft() {
        this.setBackground(LEFT_IMAGE);
    } */

    public Rectangle getFeet() {
        return new Rectangle(getX(), getY() + getHeight() - 15, 55, 15);
    }
}
