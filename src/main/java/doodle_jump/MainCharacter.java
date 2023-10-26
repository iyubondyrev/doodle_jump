package doodle_jump;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

import game_engine.GameObject;
import physics.Vector;
import utils.ImageUploader;

/**
 * Doodle.
 */
public class MainCharacter extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("LeftDoodle.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("RightDoodle.png");
    
    private static final Vector JUMP_VECTOR = new Vector(0., -13.);
    private static final Vector MOOVING_BOOST = new Vector(1., 0.);

    private static final int TELEPORT_OFFSET = LEFT_IMAGE.getWidth(null) / 2;
    private static final double MAXIMAL_X_SPEED = 6.0;

    private static final int RECT_HEIGHT = 15;
    private static final int RECT_WIDTH = 55;

    private boolean right = true;
    public int score = -200;

    public MainCharacter(int x, int y) {
        super(x, y, RIGHT_IMAGE);
    }

    public MainCharacter() {
        this(0, 0);
    }

    public void moveLeft() {
        this.subtractFromBoostVector(MOOVING_BOOST);
    }

    public void moveRight() {
        this.addToBoostVector(MOOVING_BOOST);
    }
    
    public void changeImage() {
        if (right) {
            this.setIcon(new ImageIcon(LEFT_IMAGE));
            this.right = false;
        } else {
            this.setIcon(new ImageIcon(RIGHT_IMAGE));
            this.right = true;
        }
    }

    public void jumpOnPlatforms(PlatformCollection platforms) {
        for (Platform platform : platforms.getCollection()) {
            if (this.getSpeedVector().y > 0 
                && this.getRectangle().intersects(platform.getRectangle())) {
                this.jump();
            }
        }
    }

    public void slowHorizontaly() {
        double horizontalSpeed = this.getSpeedVector().getX();
        if (horizontalSpeed * horizontalSpeed < 0.01) {
            this.getSpeedVector().x = 0;
            return;
        }
        double resistance = Math.abs(horizontalSpeed) * 0.015;
        if (horizontalSpeed > 0) {
            this.getSpeedVector().x = (horizontalSpeed - resistance);
            return;
        }
        if (horizontalSpeed < 0) {
            this.getSpeedVector().x = (horizontalSpeed + resistance);
        }
    }

    public void move() {
        // this.correctBoostVector();
        this.speedVector.add(this.boostVector);
        this.correctSpeedVector();

        if (this.getY() < PlatformCollection.MOVE_LIM) {
            this.setCoordinateY(PlatformCollection.MOVE_LIM);
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
        this.right = true;
        this.setIcon(new ImageIcon(RIGHT_IMAGE));
        this.score = 0;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.getX(), this.getY() + this.getHeight() - RECT_HEIGHT,
        RECT_WIDTH, RECT_HEIGHT);
    }
}
