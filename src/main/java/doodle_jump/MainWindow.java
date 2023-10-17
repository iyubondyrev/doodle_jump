package doodle_jump;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;

import doodle_jump.Game;

public class MainWindow extends JFrame {

    public MainWindow(int width, int height) {
        super();
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
