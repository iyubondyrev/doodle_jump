package doodle_jump;

import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import doodle_jump.MainWindow;
import physics.Vector;
import utils.ImageUploader;
import doodle_jump.Doodle;
import doodle_jump.PlatformCollection;
import game_engine.AnimationPanel;

public class Game extends AnimationPanel {
    private MainWindow window;

    private int scores;
    private Doodle doodle;
    private PlatformCollection platforms;

    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;

    private static final Image BG_IMAGE = ImageUploader.upload("bg.png");
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);
    
    public Game() {
        super(BG_IMAGE);
        this.window = new MainWindow(WIDTH, HEIGHT);
        this.window.add(this);
        this.window.addWindowFocusListener(this.getWindowAdapter());
    }

    private WindowAdapter getWindowAdapter() {
        return new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                runAnimation();
            }
        };
    }

    private void showStartScreen() {

    }

    private void showGame() {
        this.initGame();
        this.runAnimation();
    }

    @Override
    protected void preAction() {
        // Gravity vector implies on doodle.
        this.doodle.addAcceleration(GRAVITY_VECTOR);
        // Doodle moves.
        this.doodle.move();
    }

    @Override
    protected void postAction() {
        this.window.validate();
    }

    private void initGame() {
        this.doodle = new Doodle(100, 100);
        this.platforms = new PlatformCollection();
        this.scores = 0;

        this.add(this.doodle);
        this.add(this.platforms);

        // Example action: creating 2 platforms.
        this.platforms.createPlatform();
    }

    private void showResultScreen() {

    }

    /**
     * Show start screen, play game and show results.
     * Main method of class.
     */
    public void play() {
        this.showStartScreen();
        this.showGame();
        this.showResultScreen();
    }
}