package game_engine;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Base surface to draw other elements on it.
 * It can contain background or be transparant.
 */
public abstract class BaseSurface extends JPanel {
    private Image image = null;

    /**
     * Constructor.
     * 
     * @param image background.
     */
    public BaseSurface(Image image) {
        this(image.getWidth(null), image.getHeight(null));
        this.image = image;
    }

    /**
     * Constructor.
     * 
     * @param width  surface width.
     * @param height surface height.
     */
    public BaseSurface(int width, int height) {
        super(null);
        this.setLocation(0, 0);
        this.setOpaque(false);
        this.setSize(width, height);
    }

    /**
     * Draw surface.
     * Method is called implicitly.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.image != null) {
            g.drawImage(
                    this.image, 0, 0,
                    this.image.getWidth(null),
                    this.image.getHeight(null),
                    this);
        }
    }
}
