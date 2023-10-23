package game_engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public abstract class BaseElement2 extends JLabel {
    protected ImageIcon image;
    protected Point2D.Double coordinates;

    /**
     * Constructor.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     * @param image image.
     */
    public BaseElement2(int x, int y, Image image) {
        super(new ImageIcon(image));
        this.setOpaque(false);

        // this.image = image;
        // this.setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        this.setSize(image.getWidth(null), image.getHeight(null));

        this.coordinates = new Point2D.Double();
        this.setCoordinates(x, y);
    }

    /**
     * Constructor to create with coordinates (0, 0).
     * @param image image.
     */
    public BaseElement2(Image image) {
        this(0, 0, image);
    }
    
    // /**
    //  * Set image.
    //  * @param image image.
    //  */
    // public void setImage(Image image) {
    //     this.image = image;
    // }

    // /**
    //  * Get image.
    //  * @return image.
    //  */
    // public Image getImage() {
    //     return this.image;
    // }

    /**
     * Set coordinates in floating-point format (double).
     * JPanel.setLocation gets coordinates in integer format after casting from double to int.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(double x, double y) {
        this.setLocation((int) x, (int) y);
        this.coordinates.setLocation(x, y);
    }

    /**
     * Set coordinates in integer format (int).
     * JPanel.setLocation gets coordinates in integer format without casting.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(int x, int y) {
        this.setLocation(x, y);
        this.coordinates.setLocation((double) x, (double) y);
    }

    /**
     * Set x-coordinate in floating-point format (double).
     * @param x x-coordinate of top-left corner.
     */
    public void setCoordinateX(double x) {
        this.setCoordinates(x, this.coordinates.getY());
    }

    /**
     * Set x-coordinate in integer format (int).
     * WARMING: y-coordinate will be rounded down automatically in a result of casting to int.
     * @param x x-coordinate of top-left corner.
     */
    public void setCoordinateX(int x) {
        this.setCoordinates(x, this.getY());
    }

    /**
     * Set y-coordinates in floating-point format (double).
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinateY(double y) {
        this.setCoordinates(this.coordinates.getX(), y);
    }

    /**
     * Set y-coordinates in integer format (int).
     * WARMING: x-coordinate will be rounded down automatically in a result of casting to int.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinateY(int y) {
        this.setCoordinates(this.getX(), y);
    }

    /**
     * Get x-coordinate.
     * @return x-coordinate.
     */
    public double getCoordinateX() {
        return this.coordinates.getX();
    }

    /**
     * Get y-coordinate.
     * @return y-coordinate.
     */
    public double getCoordinateY() {
        return this.coordinates.getY();
    }
}