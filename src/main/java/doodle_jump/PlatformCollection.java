package doodle_jump;

import utils.ImageUploader;

import java.awt.Image;

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
            p.setLocation(x, y);
            this.list.add(p);
            this.add(p);
        }

    }
}
