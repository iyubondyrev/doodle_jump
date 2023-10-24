package game_engine;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import utils.ImageUploader;

public class Window extends JFrame {

    private static final Image ICON_IMAGE = ImageUploader.upload("icon.png");

    public Window(int width, int height) {
        super();
        this.setIconImage(ICON_IMAGE);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}

