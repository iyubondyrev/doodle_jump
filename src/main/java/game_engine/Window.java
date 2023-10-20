package game_engine;

import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(int width, int height) {
        super();
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

