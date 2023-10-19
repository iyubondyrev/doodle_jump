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

import doodle_jump.MainWindow;
import physics.Vector;
import utils.ImageUploader;
import doodle_jump.Doodle;
import doodle_jump.PlatformCollection;
import game_engine.AnimationPanel;

/**
 * Class represents game.
 */
public class Game extends AnimationPanel {
    private MainWindow window;

    private boolean flag = false;

    private Graphics2D g;

    private int scores;
    private Doodle doodle;
    private PlatformCollection platforms;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    public static final int UP_LIMIT = 10;

    private static final Image BG_IMAGE = ImageUploader.upload("bg.png");
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);

    /**
     * Constructor.
     */
    public Game() {
        super(BG_IMAGE);
        this.window = new MainWindow(WIDTH, HEIGHT);
        this.window.add(this);
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
        this.doodle.addAcceleration(GRAVITY_VECTOR);
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