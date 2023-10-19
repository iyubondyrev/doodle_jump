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

    /**
     * Constructor to create with coordinates (0, 0).
     * @param image image.
     */
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

    /**
     * Set coordinates in floating-point format (double).
     * JPanel.setLocation gets coordinates in integer format after casting.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(double x, double y) {
        this.setLocation((int) x, (int) y);
        this.point.setCoordinates(x, y);
    }

    /**
     * Set coordinates in integer format (int).
     * JPanel.setLocation gets coordinates in integer format without casting.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(int x, int y) {
        this.setLocation(x, y);
        this.point.setCoordinates((double) x, (double) y);
    }

    /**
     * Set x-coordinate.
     * @param x x-coordinate of top-left corner.
     */
    public void setCoordinateX(double x) {
        this.setCoordinates(x, this.point.getX());
    }

    /**
     * Set y-coordinates.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinateY(double y) {
        this.setCoordinates(this.point.getY(), y);
    }

    /**
     * Get x-coordinate.
     * @return x-coordinate.
     */
    public double getCoordinateX() {
        return this.point.getX();
    }

    /**
     * Get y-coordinate.
     * @return y-coordinate.
     */
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