package doodle_jump;

import game_engine.BaseGame;
import game_engine.Window;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import physics.Vector;
import utils.ImageUploader;

/**
 * Class represents game.
 */
public class Game extends BaseGame {
    private MainCharacter doodle;
    private PlatformCollection platforms;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    public static final int UP_LIMIT = 100;

    public static final int LIMIT_OFFSET = 50;
    public static final int X_LEFT_LIMIT = -LIMIT_OFFSET;
    public static final int X_RIGHT_LIMIT = WIDTH + LIMIT_OFFSET;

    private static final Image BG_IMAGE = ImageUploader.upload("bg.png");
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);

    /**
     * Constructor.
     */
    public Game() {
        super(WIDTH, HEIGHT, BG_IMAGE);
        this.addKeyListener(new MovingDoodleKeyListener());
    }

    /**
     * Show start scren.
     */
    private void showStartScreen() {

    }

    /**
     * Play game.
     */
    private void playGame() {
        this.initGame();
        this.runAnimation();
    }

    private void endGame() {
        this.doodle.totalStop();
        this.removeAll();
        this.repaint();
        this.stopAnimation();
    }

    @Override
    /**
     * Main action of game.
     */
    protected void actionBegin() {
        if (this.doodle.getY() > this.getHeight()) {
            endGame();
        } else {
            for (Platform p : this.platforms.list) {
                if (doodle.getSpeedVector().y > 0
                        && doodle.getRectangle().intersects(p.getRectangle())) {
                    // doodle.setCoordinateY((int) p.getBounds().getY() - doodle.getHeight());
                    doodle.jump();
                }
            }

            this.platforms.movePlatforms(this.doodle);
            this.doodle.move();
        }
    }

    @Override
    /**
     * Render game.
     */
    protected void actionEnd() {
    }

    /**
     * Init game.
     * Init all objects on panel and add them on panel.
     */
    private void initGame() {
        this.doodle = new MainCharacter(100, 100);
        this.platforms = new PlatformCollection();

        this.add(this.doodle);
        this.add(this.platforms);
        // this.animatedElements.add(this.doodle);

        this.platforms.genNewPlatforms();

        this.doodle.setBoostVector(GRAVITY_VECTOR);
    }

    /**
     * Show screen with results.
     */
    private void showResultScreen() {
        System.out.println("Game is over");
    }

    /**
     * Show start screen, play game and show results.
     * Main method of class.
     */
    public void play() {
        this.showStartScreen();
        this.playGame();
        // this.showResultScreen();
    }

    private class MovingDoodleKeyListener extends KeyAdapter {

        private boolean right = false;
        private boolean left = false;

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                doodle.moveLeft();
                left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                doodle.moveRight();
                right = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && left) {
                doodle.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && right) {
                doodle.moveLeft();
            }
        }
    }
}