package doodle_jump;

import game_engine.GameObject;
import java.awt.Image;
import java.awt.Rectangle;
import physics.Vector;
import utils.ImageUploader;

public class MainCharacter extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("doodleLeft.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("doodleRight.png");
    private static final Vector JUMP_VECTOR = new Vector(0, -10);
    private static final Vector MOVING_VECTOR = new Vector(0.3, 0);
    

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

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY() + getHeight() - 15, 55, 15);
    }
}
