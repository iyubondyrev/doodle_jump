package doodle_jump;

import game_engine.BaseAnimation;
import game_engine.BaseGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;
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
    public static final String RECORD_FILE_PATH = "src/main/java/resourses/record.txt";
    private int currentScore = 0;

    private static final Image BG_IMAGE = ImageUploader.upload("GridBackground.png");
    private static final Vector GRAVITY_VECTOR = new Vector(0, 0.2);
    private MovingDoodleKeyListener moveKeyListener = new MovingDoodleKeyListener();
    private EscListener escKeyListener = new EscListener();
    private MainLevel mainGame;
    private BaseAnimation initScreen;

    /**
     * Constructor.
     */
    public Game() {
        super(WIDTH, HEIGHT, BG_IMAGE);
        this.addKeyListener(moveKeyListener);
        this.addKeyListener(escKeyListener);
        try {
            File myObj = new File(RECORD_FILE_PATH);
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(RECORD_FILE_PATH);
                myWriter.write(String.valueOf(0));
                myWriter.close();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
    
    /**
     * The main plot of game.
     */
    @Override
    protected void execGamePlot() {
        this.initScreen = new InitialScreen();
        this.initScreen.exec();
        while (true) {
            this.returnFocus();
            this.mainGame = new MainLevel();
            mainGame.exec();
            this.initScreen = new InitialScreen();
            this.initScreen.exec();
        }
        
    }

    private class EscListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                mainGame.stopAnimation();
            }
        }
    }

    private class MovingDoodleKeyListener extends KeyAdapter {
 
        private boolean left = false;

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                doodle.moveLeft();
                if (!left) {
                    doodle.changeImage();
                }
                left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                doodle.moveRight();
                if (left) {
                    doodle.changeImage();
                }
                left = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && left) {
                doodle.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !left) {
                doodle.moveLeft();
            }
        }
    }

    /**
     * Initial screen.
     * Show record of user, button 'start game' and 'quit'.
     */
    private class InitialScreen extends BaseAnimation {

        private JPanel panel;
        
        @Override
        protected void actionInit() {
            panel = new JPanel();
            panel.setFocusable(true);
            panel.requestFocusInWindow();
            panel.setLayout(new GridLayout(4, 1));

            JLabel textLabel = new JLabel("Welcome to Doodle Jump!", SwingConstants.CENTER);
            textLabel.setFont(new Font("Arial", Font.BOLD, 25));
            panel.add(textLabel);

            int record = 0;
            try {
                File myObj = new File(RECORD_FILE_PATH);
                Scanner myReader = new Scanner(myObj);
                record = myReader.nextInt();
                myReader.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            JLabel recordLabel = new JLabel("Your record is: " + record + " good job!",
                SwingConstants.CENTER);
            recordLabel.setFont(new Font("Arial", Font.BOLD, 25));
            panel.add(recordLabel);

            JButton startButton = new JButton("Start Game");
            startButton.setBackground(new Color(152, 251, 152));
            startButton.setForeground(Color.BLACK);
            startButton.setOpaque(true);
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopAnimation();
                }
            });
            panel.add(startButton);


            JButton quitButton = new JButton("Quit");
            quitButton.setBackground(new Color(255, 105, 97));
            quitButton.setForeground(Color.BLACK);
            quitButton.setOpaque(true);
            quitButton.setBorderPainted(false);
            quitButton.setFocusPainted(false);
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            panel.add(quitButton);


            window.add(panel);
            panel.setVisible(true);

        }

        @Override
        protected void actionClose() {
            window.remove(panel);
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
     * Main level.
     * Doodle jumps from one platform to another in order to reach the highest point.
     */
    private class MainLevel extends BaseAnimation {
        private JLabel scoreLabel;

        @Override
        protected void actionInit() {
            doodle = new MainCharacter(100, 100);
            platforms = new PlatformCollection();

            scoreLabel = new JLabel("Score: 0");
            scoreLabel.setFont(new Font("Courier", Font.ITALIC, 20));
            scoreLabel.setForeground(new Color(92, 72, 18));
            scoreLabel.setBounds(5, 0, 200, 30); // You might need to adjust the position
            add(scoreLabel);
            scoreLabel.setVisible(true);
                

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
                doodle.slowHorizontaly();
                doodle.move();
                scoreLabel.setText("Score: " + doodle.score);
                scoreLabel.repaint();
            }
        }

        @Override
        protected void actionClose() {
            moveKeyListener.left = false;
            currentScore = doodle.score;
            try {
                File myObj = new File(RECORD_FILE_PATH);
                Scanner myReader = new Scanner(myObj);
                int record = 0;
                try {
                    record = myReader.nextInt();
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                if (currentScore > record) {
                    FileWriter myWriter = new FileWriter(RECORD_FILE_PATH);
                    myWriter.write(String.valueOf(currentScore));
                    myWriter.close();
                }
                myReader.close();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
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
}