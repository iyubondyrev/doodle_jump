package doodle_jump;

import utils.ImageUploader;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import game_engine.GameObject;
import game_engine.GameObjectCollection;
import doodle_jump.Game;

class Platform extends GameObject {
    private static final Image IMAGE = ImageUploader.upload("platform.png");

    public Platform(int x, int y) {
        super(x, y, IMAGE);
    }

    public Platform() {
        super(0, 0, IMAGE);
    }

    public Rectangle getBase(){
        return new Rectangle(getX(), getY(), getWidth(), 2);
    }
}

/**
 * 
 */
public class PlatformCollection extends GameObjectCollection<Platform> {

    public PlatformCollection() {
        super(Game.WIDTH, Game.HEIGHT);
    }

    public void genNewPlatforms() {
        for (int i = 0; i < 10; i++) {
            Platform p = new Platform();
            int x = (int) (Math.random() * (getWidth() - p.getWidth()));
            int y = i * ((int) (Math.random() * 50) + 70);
            p.setCoordinates(x, y);
            this.addObj(p);
        }

    }

    private static final int STAGE_SCROLL_LIMIT = 300;

    public void moveStageUp(MainCharacter doodle) {
        if (doodle.getY() < STAGE_SCROLL_LIMIT) {
            int offset = STAGE_SCROLL_LIMIT - doodle.getY();
            doodle.setCoordinateY(STAGE_SCROLL_LIMIT);
            for (Platform p : this.list) {
                p.setLocation(p.getX(), p.getY() + offset);
                if (p.getY() > this.getHeight()) {
                    p.setLocation((int) (Math.random() * (getWidth() - p.getWidth())),
                            ((int) (Math.random() * 50) - 50));
                }
            }
        }
    }
}
