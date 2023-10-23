package doodle_jump;

import game_engine.GameObject;
import java.awt.Image;
import java.awt.Rectangle;
import physics.Vector;
import utils.ImageUploader;

public class MainCharacter extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("doodleLeft.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("doodleRight.png");
    private static final Vector JUMP_VECTOR = new Vector(0., -13.);
    private static final Vector MOVING_VECTOR = new Vector(1., 0.);


    private static final double MAXIMAL_X_SPEED = 6.0;
    private static final double MAXIMAL_X_BOOST = 3.;
    private static final double GAME_WIDTH = 500;
    

    public MainCharacter(int x, int y) {
        super(x, y, RIGHT_IMAGE, true, Game.X_LEFT_LIMIT, Game.X_RIGHT_LIMIT);
    }

    public MainCharacter() {
        this(0, 0);
    }

    public void moveLeft() {
        this.subtractFromBoostVector(MOVING_VECTOR);
    }

    public void moveRight() {
        this.addToBoostVector(MOVING_VECTOR);
    }


    public void move() {
        this.correctBoostVector();
        this.speedVector.add(this.boostVector);
        this.correctSpeedVector();

        int stageMoveLimit = 300;
        if (this.getY() < stageMoveLimit) {
            this.setCoordinateY(stageMoveLimit);
        }

        super.move();

        int halfDoodle = this.getWidth() / 2;
        if (this.getX() < -halfDoodle) {
            this.setCoordinateX(GAME_WIDTH - halfDoodle);
        } else if (this.getX() > GAME_WIDTH - halfDoodle) {
            this.setCoordinateX(-halfDoodle);
        }
    }

    private void correctBoostVector() {
        if (Math.abs(this.boostVector.getX()) > MAXIMAL_X_BOOST) {
            this.boostVector.setLocation(
                Math.signum(this.boostVector.getX()) * MAXIMAL_X_BOOST,
                this.boostVector.getY());
        }
    }

    private void correctSpeedVector() {
        if (Math.abs(this.speedVector.getX()) > MAXIMAL_X_SPEED) {
            this.speedVector.setLocation(
                Math.signum(this.speedVector.getX()) * MAXIMAL_X_SPEED,
                this.speedVector.getY());
        }
    }

    public void jump() {
        this.speedVector.setLocation(this.speedVector.getX(), 0.0);
        this.addToSpeedVector(JUMP_VECTOR);
    }

    public void totalStop() {
        this.setSpeedVector(null);
        this.setBoostVector(null);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY() + getHeight() - 15, 55, 15);
    }
}
