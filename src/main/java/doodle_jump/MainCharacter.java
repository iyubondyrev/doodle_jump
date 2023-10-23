package doodle_jump;

import java.awt.Image;
import java.awt.Rectangle;

import game_engine.GameObject;
import physics.Vector;
import utils.ImageUploader;

public class MainCharacter extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("doodleLeft.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("doodleRight.png");
    
    private static final Vector JUMP_VECTOR = new Vector(0., -13.);
    private static final Vector MOVING_VECTOR = new Vector(1., 0.);

    private static final int TELEPORT_OFFSET = LEFT_IMAGE.getWidth(null) / 2;
    private static final double MAXIMAL_X_SPEED = 6.0;
    private static final double MAXIMAL_X_BOOST = 3.;

    private static final int RECT_HEIGHT = 15;
    private static final int RECT_WIDTH = 55;

    public MainCharacter(int x, int y) {
        super(x, y, LEFT_IMAGE);
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

    public void jumpOnPlatforms(PlatformCollection platforms) {
        for (Platform platform : platforms.getCollection()) {
            if (this.getSpeedVector().y > 0 &&
                    this.getRectangle().intersects(platform.getRectangle())) {
                this.setCoordinateY(platform.getBounds().getY() - this.getHeight());
                this.jump();
            }
        }
    }

    public void move() {
        // this.correctBoostVector();
        this.speedVector.add(this.boostVector);
        this.correctSpeedVector();

        if (this.getY() < PlatformCollection.STAGE_MOVE_LIMIT) {
            this.setCoordinateY(PlatformCollection.STAGE_MOVE_LIMIT);
        }

        super.move();
        this.teleportThroughWall();
    }

    private void teleportThroughWall() {
        if (this.getX() < -TELEPORT_OFFSET) {
            this.setCoordinateX(Game.WIDTH - TELEPORT_OFFSET);
        } else if (this.getX() > Game.WIDTH - TELEPORT_OFFSET) {
            this.setCoordinateX(-TELEPORT_OFFSET);
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
        return new Rectangle(this.getX(), this.getY() + this.getHeight() - RECT_HEIGHT,
        RECT_WIDTH, RECT_HEIGHT);
    }
}
