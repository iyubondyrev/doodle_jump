package doodle_jump;

import game_engine.GameObject;
import game_engine.GameObjectCollection;
import java.awt.Image;
import java.awt.Rectangle;
import utils.ImageUploader;

/**
 * Platform.
 */
class Platform extends GameObject {
    private static final Image IMAGE = ImageUploader.upload("GreenPlatform.png");
    private static final int RECT_HEIGHT = 2;

    public Platform(int x, int y) {
        super(x, y, IMAGE);
    }

    public Platform() {
        super(0, 0, IMAGE);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), RECT_HEIGHT);
    }
}

/**
 * Class for convenient management of the platform set.
 */
public class PlatformCollection extends GameObjectCollection<Platform> {
    public static final int MOVE_LIM = 300;

    private static final int INITIAL_PLATFORMS_NUMBER = 10;
    private static final int Y_COORDINATE_OFFSET = 50;

    public PlatformCollection() {
        super(Game.WIDTH, Game.HEIGHT);
    }

    /**
     * Generate initial set of platfroms.
     */
    public void genNewPlatforms() {
        for (int i = 0; i < INITIAL_PLATFORMS_NUMBER; i++) {
            Platform p = new Platform();
            double x = Math.random() * (getWidth() - p.getWidth());
            double y = i * ((Math.random() * 50) + 70);
            p.setCoordinates(x, y);
            this.addObj(p);
        }
    }

    /**
     * Move platforms with respect to doodle.
     * 
     * @param doodle main character.
     */
    public void movePlatforms(MainCharacter doodle) {
        if (doodle.getY() < MOVE_LIM) {
            int difference = MOVE_LIM - doodle.getY();
            doodle.score += difference;

            for (Platform platform : this.list) {
                platform.setCoordinates(platform.getX(), platform.getY() + difference);

                if (platform.getY() > this.getHeight()) {
                    platform.setCoordinates(
                            Math.random() * (this.getWidth() - platform.getWidth()),
                            (Math.random() * Y_COORDINATE_OFFSET) - Y_COORDINATE_OFFSET);
                }
            }
        }
    }
}
