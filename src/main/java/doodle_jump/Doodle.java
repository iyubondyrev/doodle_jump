package doodle_jump;

import java.awt.Image;

import utils.ImageUploader;
import game_engine.GameObject;
import physics.Vector;

public class Doodle extends GameObject {
    private static final Image LEFT_IMAGE = ImageUploader.upload("doodleLeft.png");
    private static final Image RIGHT_IMAGE = ImageUploader.upload("doodleRight.png");
    private static final Vector JUMP_VECTOR = new Vector(12, 90);
    private static final Vector MOVING_VECTOR = new Vector(0.3, 0);

    public Doodle(int x, int y) {
        super(x, y, LEFT_IMAGE);
    }

    public Doodle() {
        this(0, 0);
    }
}
