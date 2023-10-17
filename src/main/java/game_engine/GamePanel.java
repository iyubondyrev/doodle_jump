package game_engine;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

import physics.Point;

public class GamePanel extends JPanel {
    private Image image;
    private Point<Integer> point;

    /**
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     * @param image image.
     */
    public GamePanel(int x, int y, Image image) {
        super(null);
        this.setOpaque(false);

        this.image = image;
        this.setSize(this.image.getWidth(null), this.image.getHeight(null));
        this.point = new Point<Integer>(x, y);
    }

    public GamePanel(Image image) {
        this(0, 0, image);
    }
    
    /**
     * Set image.
     * @param image image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Get image.
     * @return image.
     */
    public Image getImage() {
        return this.image;
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.point.setLocation(x, y);
    }

    public void setX(int x) {
        this.setLocation(x, this.getY());
    }

    public void setY(int y) {
        this.setLocation(this.getX(), y);
    }

    public int getX() {
        return this.point.getX();
    }

    public int getY() {
        return this.point.getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(
                this.image, 0, 0, this.image.getWidth(null),
                this.image.getHeight(null), this);
        }
    }

}
