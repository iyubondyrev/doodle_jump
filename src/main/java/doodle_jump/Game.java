package doodle_jump;

import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import physics.Vector;
import utils.ImageUploader;
import doodle_jump.Doodle;
import doodle_jump.PlatformCollection;
import game_engine.BaseGame;

/**
 * Class represents game.
 */
public class Game extends BaseGame {
    private boolean flag = false;

    private Graphics2D g;

    private int scores;
    private Doodle doodle;
    private PlatformCollection platforms;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    public static final int UP_LIMIT = 10;

    public static final int LIMIT_OFFSET = 50;
    public static final int X_LEFT_LIMIT = -LIMIT_OFFSET;
    public static final int X_RIGHT_LIMIT = WIDTH + LIMIT_OFFSET;

    private static final Image BG_IMAGE = ImageUploader.upload("bg.png");
    // private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.01);

    /**
     * Constructor.
     */
    public Game() {
        super(WIDTH, HEIGHT, BG_IMAGE);
        this.addKeyListener(new DoodleGameKeyListener());
        this.setFocusable(true); // Make sure the panel is focusable
        this.requestFocusInWindow(); // Request focus on the panel
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

    @Override
    /**
     * Main action of game.
     */
    protected void preAction() {
        // Gravity vector implies on doodle.
        this.doodle.addToSpeedVector(GRAVITY_VECTOR);
        // Doodle moves.
        this.doodle.move();
    }

    @Override
    /**
     * Render game.
     */
    protected void postAction() {
        if (this.flag) {
            this.platforms.moveStageUp();
            System.out.println("Flag is true");
            this.flag = false;
        }
    }
    
    /**
     * Init game.
     * Init all objects on panel and add them on panel.
     */
    private void initGame() {
        this.doodle = new Doodle(100, 100);
        this.platforms = new PlatformCollection();
        this.scores = 0;

        this.add(this.doodle);
        this.add(this.platforms);

        this.platforms.genNewPlatforms();

        // Set initial speed limit for doodle.
        this.doodle.setSpeedVector(-6, -0.5);
    }

    /**
     * Show screen with results.
     */
    private void showResultScreen() {

    }

    /**
     * Show start screen, play game and show results.
     * Main method of class.
     */
    public void play() {
        this.showStartScreen();
        this.playGame();
        this.showResultScreen();
    }

    private class DoodleGameKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            Game.this.requestFocusInWindow();
            if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Check if the pressed key is the space bar
                System.out.println("Space Pressed");
                flag = true;
            }
        }
    }
}