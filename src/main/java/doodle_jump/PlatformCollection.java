package doodle_jump;


import game_engine.GameObject;
import game_engine.GameObjectCollection;
import java.awt.Image;
import java.awt.Rectangle;
import utils.ImageUploader;


class Platform extends GameObject {
    private static final Image IMAGE = ImageUploader.upload("platform.png");

    public Platform(int x, int y) {
        super(x, y, IMAGE);
    }

    public Platform() {
        super(0, 0, IMAGE);
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), 2);
    }
}

public class PlatformCollection extends GameObjectCollection<Platform> {

    public PlatformCollection() {
        super(Game.WIDTH, Game.HEIGHT);
    }

    public void genNewPlatforms() {
        for (int i = 0; i < 10; i++) {
            Platform p = new Platform();
            double x = Math.random() * (getWidth() - p.getWidth());
            double y = i * ((Math.random() * 50) + 70);
            p.setCoordinates(x, y);
            this.addObj(p);
        }

    }

    public void movePlatforms(MainCharacter doodle) {
        int stageMoveLimit = 300;
        if (doodle.getY() < stageMoveLimit) {
            int difference = stageMoveLimit - doodle.getY();

            for (Platform platfrorm : this.list) {
                platfrorm.setCoordinates(platfrorm.getX(), platfrorm.getY() + difference);
                
                if (platfrorm.getY() > this.getHeight()) {
                    platfrorm.setCoordinates(
                        Math.random() * (this.getWidth() - platfrorm.getWidth()),
                        (Math.random() * 50) - 50);
                }
            }
        }
    }
}
