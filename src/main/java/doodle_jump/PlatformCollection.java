package doodle_jump;

import utils.ImageUploader;

import java.awt.Image;

import game_engine.GameObject;
import game_engine.GameObjectCollection;
import doodle_jump.Game;

class Platform extends GameObject{
    private static final Image IMAGE = ImageUploader.upload("platform.png");

    public Platform(int x, int y) {
        super(x, y, IMAGE);
    }
}

/**
 * 
 */
public class PlatformCollection extends GameObjectCollection<Platform>{

    public PlatformCollection() {
        super(Game.WIDTH, Game.HEIGHT);
    }

    /**
     * Example function to show how create platforms.
     */
    public void createPlatform() {
        Platform platform1 = new Platform(300, 40);
        Platform platform2 = new Platform(100, 400);
        this.addObj(platform1);
        this.addObj(platform2);
    }
}
