package game_engine;

import java.awt.Image;
import javax.swing.JFrame;
import utils.ImageUploader;

/**
 * Basic window class for main frame in the game.
 */
public class Window extends JFrame {

    private static final Image ICON_IMAGE = ImageUploader.upload("icon.png");

    /**
     * Constructor.
     * @param width width of the window
     * @param height height of the window
     */
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
