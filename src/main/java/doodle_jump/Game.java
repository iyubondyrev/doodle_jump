package doodle_jump;

import game_engine.BaseGame;
import game_engine.BaseAnimation;
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

    private static final Image BG_IMAGE = ImageUploader.upload("bg.png");
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);

    /**
     * Constructor.
     */
    public Game() {
        super(WIDTH, HEIGHT, BG_IMAGE);
        this.addKeyListener(new MovingDoodleKeyListener());
    }

    @Override
    protected void execGamePlot() {
        new StartGame().exec();
        new MainLevel().exec();
        new FinalResult().exec();

        // Exit from program.
        System.exit(0);
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

    /**
     * Start screen.
     * Show button 'Start game'.
     */
    private class StartGame extends BaseAnimation {

        @Override
        protected void actionBegin() {
            this.stopAnimation();
        }

        @Override
        protected void render() {
            window.validate();
        }
    }

    /**
     * Main level.
     * Doodle jumps from one platform to another in order to reach the highest point.
     */
    private class MainLevel extends BaseAnimation {

        @Override
        protected void actionInit() {
            doodle = new MainCharacter(100, 100);
            platforms = new PlatformCollection();

            add(doodle);
            add(platforms);

            platforms.genNewPlatforms();
            doodle.setBoostVector(GRAVITY_VECTOR);
        }

        @Override
        protected void actionBegin() {
            if (doodle.getY() > getHeight()) {
                this.stopAnimation();
            } else {
                doodle.jumpOnPlatforms(platforms);
                platforms.movePlatforms(doodle);
                doodle.move();
            }
        }

        @Override
        protected void actionClose() {
            doodle.totalStop();
            removeAll();
            repaint();
            stopAnimation();
        }

        @Override
        protected void render() {
            window.validate();
        }
    }

    /**
     * Final screen.
     * Show results of user.
     */
    private class FinalResult extends BaseAnimation {

        @Override
        protected void actionBegin() {
            this.stopAnimation();
        }
        
        @Override
        protected void render() {
            window.validate();
        }
    }
}