package game_engine;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

import physics.Point;

public class GamePanel extends JPanel {
    private Image image;
    private Point<Double> point;

    /**
     * Constructor.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     * @param image image.
     */
    public GamePanel(int x, int y, Image image) {
        super(null);
        this.setOpaque(false);

        this.image = image;
        this.setSize(this.image.getWidth(null), this.image.getHeight(null));
        this.point = new Point<Double>();
        this.setCoordinates(x, y);
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

    public void setCoordinates(double x, double y) {
        this.setLocation((int) x, (int) y);
        this.point.setCoordinates(x, y);
    }

    public void setCoordinates(int x, int y) {
        this.setLocation(x, y);
        this.point.setCoordinates((double) x, (double) y);
    }

    public void setCoordinateX(double x) {
        this.setCoordinates(x, this.point.getX());
    }

    public void setCoordinateY(double y) {
        this.setCoordinates(this.point.getY(), y);
    }

    public double getCoordinateX() {
        return this.point.getX();
    }

    public double getCoordinateY() {
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