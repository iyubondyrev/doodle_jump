package doodle_jump;

import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import physics.Vector;
import utils.ImageUploader;
import doodle_jump.MainCharacter;
import doodle_jump.PlatformCollection;
import game_engine.BaseGame;

/**
 * Class represents game.
 */
public class Game extends BaseGame {
    private boolean flag = false;

    private Graphics2D g;

    private int scores;
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

    private void gameOver() {
        this.doodle.totalStop();
        this.removeAll();
        this.playGame();
        this.repaint();
    }

    @Override
    /**
     * Main action of game.
     */
    protected void preAction() {
        if (doodle.getY() > getHeight()) {
            gameOver();
        } else {
            double x = doodle.getSpeedVector().getX();
            if (Math.abs(x) < 0.1) {
                doodle.getSpeedVector().x = 0;
            } else {
                double friction = Math.abs(x) * 0.02; // TODO fix this constant
                if (x > 0) {
                    doodle.getSpeedVector().x = (x - friction);
                } else if (x < 0) {
                    doodle.getSpeedVector().x = (x + friction);
                }
            }

            /* if (doodle.getSpeedVector().x > Doodle.MOVING_VECTOR.x) {
                doodle.turnRight();
            } else if (doodle.getVelocity().x < -Doodle.MOVING_VECTOR.x) {
                doodle.turnLeft();
            } */

            int halfDoodle = doodle.getWidth() / 2;
            if (doodle.getX() < -halfDoodle) {
                doodle.setCoordinateX(getWidth() - halfDoodle);
            } else if (doodle.getX() > getWidth() - halfDoodle) {
                doodle.setCoordinateX(-halfDoodle);
            }

            for (Platform p : this.platforms.list) {
                System.out.println("Y of speed vector; ");
                System.out.println(doodle.getSpeedVector().y);
                System.out.println("Feets of doodle: ");
                System.out.println(doodle.getFeet());
                System.out.println("Base: ");
                System.out.println(p.getBase());
                System.out.println("Intersect: ");
                System.out.println(doodle.getFeet().intersects(p.getBase()));
                if (doodle.getSpeedVector().y > 0 && doodle.getFeet().intersects(p.getBase())) {
                    doodle.setCoordinateY((int) p.getBounds().getY() - doodle.getHeight());
                    doodle.jump();
                }
            }

            this.platforms.moveStageUp(this.doodle);
        }
    }

    @Override
    /**
     * Render game.
     */
    protected void postAction() {
    }
    
    /**
     * Init game.
     * Init all objects on panel and add them on panel.
     */
    private void initGame() {
        this.doodle = new MainCharacter(100, 100);
        this.platforms = new PlatformCollection();
        this.scores = 0;

        this.add(this.doodle);
        this.add(this.platforms);

        this.platforms.genNewPlatforms();

        this.doodle.setBoostVector(GRAVITY_VECTOR);
        this.doodle.startAnimation();
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