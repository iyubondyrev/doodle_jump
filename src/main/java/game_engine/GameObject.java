package game_engine;

import java.awt.Image;
import java.awt.Rectangle;
import physics.Vector;

/**
 * Base object with coordinates, image, boost vector and speed vector in game.
 */
public abstract class GameObject extends BaseElement {
    protected Vector speedVector;
    protected Vector boostVector;

    /**
     * Constructor.
     * 
     * @param x           x-coordinate of top-left corner.
     * @param y           y-coordinate of top-left cordner.
     * @param image       object image.
     */
    public GameObject(int x, int y, Image image) {
        super(x, y, image);
        this.speedVector = new Vector();
        this.boostVector = new Vector();
    }

    public GameObject(Image image) {
        this(0, 0, image);
    }

    /**
     * Set speed-vector.
     * 
     * @param vector speed-vector.
     */
    public void setSpeedVector(Vector vector) {
        if (vector == null) {
            vector = new Vector(0., 0.);
        }

        this.speedVector.setLocation(vector);
    }

    /**
     * Get speed vector.
     * 
     * @return speed vector.
     */
    public Vector getSpeedVector() {
        return this.speedVector;
    }

    /**
     * Set boost vector.
     * 
     * @param vector boost vector.
     */
    public void setBoostVector(Vector vector) {
        if (vector == null) {
            vector = new Vector(0., 0.);
        }

        this.boostVector.setLocation(vector);
    }

    /**
     * Get boost vector.
     * 
     * @return boost vector.
     */
    public Vector getBoostVector() {
        return this.boostVector;
    }

    /**
     * Add to boost vector.
     * 
     * @param vector adding vector.
     */
    public void addToBoostVector(Vector vector) {
        this.boostVector.add(vector);
    }

    /**
     * Subtract from boost vector.
     * 
     * @param vector subtracting vector.
     */
    public void subtractFromBoostVector(Vector vector) {
        this.boostVector.subtract(vector);
    }

    /**
     * Multiply boost vector by number.
     * 
     * @param value number.
     */
    public void multiplyBoostVector(double value) {
        this.boostVector.multiply(value);
    }

    /**
     * Add to speed-vector.
     * Method recieves vector.
     * 
     * @param addingVector adding vector.
     */
    public void addToSpeedVector(Vector addingVector) {
        this.speedVector.add(addingVector);
    }

    /**
     * Subtract from speed-vector.
     * Method receives vector.
     * 
     * @param substractingVector subtracting vector.
     */
    public void subtractFromSpeedVector(Vector substractingVector) {
        this.speedVector.subtract(substractingVector);
    }

    /**
     * Multiply speed-vector by number.
     * 
     * @param numb number.
     */
    public void multiplySpeedVector(double numb) {
        this.speedVector.multiply(numb);
    }

    /**
     * Move object.
     */
    public void move() {
        double x = this.getCoordinateX() + this.getSpeedVector().getX();
        double y = this.getCoordinateY() + this.getSpeedVector().getY();
        this.setCoordinates(x, y);
    }

    /**
     * Get rectangle which cover the whole object.
     * Rectangle is required to calculate collision.
     * 
     * @return rectangle which is used to calculate collision.
     */
    public Rectangle getRectangle() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
