package game_engine;

import physics.Vector;

import java.awt.Image;

/**
 * Class represents object with coordinates and image in game.
 */
public abstract class GameObject extends GamePanel {
    private Vector speedVector;

    // true if object teleports through walls, false - not
    private boolean teleporting;
    // the most left x-coordinate crossing which x-coordinate of object will be equal to xRightLimit
    private double xLeftLimit;
    // the most right x-coordinate crossing which x-coordinate of object will be equal to xLeftLimit
    private double xRightLimit;

    private static final double FALLING_SPEED_THRESHOLD = 0;
    private static final double X_LIMIT_OFFSET = 2;

    /**
     * Constructor.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left cordner.
     * @param image object image.
     * @param teleporting boolean (true - object teleports throught walls, false - not)
     * @param xLeftLimit the left limit for x-coordinate.
     * @param xRightLimit the right limit for x-coordinate.
     */
    public GameObject(
        int x, int y, Image image, boolean teleporting, double xLeftLimit, double xRightLimit) {
        super(x, y, image);
        this.teleporting = teleporting;
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.speedVector = new Vector();
    }

    public GameObject(int x, int y, Image image) {
        this(x, y, image, false, 0, 0);
    }

    public GameObject(Image image) {
        this(0, 0, image, false, 0, 0);
    }

    /**
     * Get of object can teleport through wall.
     * @return boolean (true - object can teleport, false - not).
     */
    public boolean isTeleporting() {
        return this.teleporting;
    }

    public double getXRightLimit() {
        return this.xRightLimit;
    }

    public double getXLeftLimit() {
        return this.xLeftLimit;
    }

    /**
     * Get speed vector.
     * @return speed vector.
     */
    public Vector getSpeedVector() {
        return this.speedVector;
    }

    /**
     * Define if object is falling.
     * @return boolean (true if object is falling, false - not).
     */
    public boolean isFalling() {
        return this.speedVector.getY() > FALLING_SPEED_THRESHOLD;
    }
    
    /**
     * Set speed-vector.
     * @param vector speed-vector.
     */
    public void setSpeedVector(Vector vector) {
        this.speedVector = vector;
    }

    /**
     * Set speed-vector.
     * @param x x-value of speed-vector.
     * @param y y-value of speed-vector.
     */
    public void setSpeedVector(double x, double y) {
        this.setSpeedVector(new Vector(x, y));
    }

    /**
     * Add to speed-vector.
     * Method recieves vector.
     * @param addingVector adding vector.
     */
    public void addToSpeedVector(Vector addingVector) {
        this.speedVector.add(addingVector);
    }

    /**
     * Add to speed-vector.
     * @param x x-value.
     * @param y y-value.
     */
    public void addToSpeedVector(double x, double y) {
        this.addToSpeedVector(new Vector(x, y));
    }

    /**
     * Subtract from speed-vector.
     * Method receives vector.
     * @param substractingVector subtracting vector.
     */
    public void subtractFromSpeedVector(Vector substractingVector) {
        this.speedVector.subtract(substractingVector);
    }

    /**
     * Subtract from speed-vector.
     * @param x x-value.
     * @param y y-value.
     */
    public void subtractFromSpeedVector(double x, double y) {
        this.subtractFromSpeedVector(new Vector(x, y));
    }

    /**
     * Multiply speed-vector by number.
     * @param numb number.
     */
    public void multiplySpeedVector(double numb) {
        this.speedVector.multiply(numb);
    }

    /**
     * Move object.
     * 
     * If object is teleporting:
     * When it crosses xLeftLimit, x-coordinate is equal to xRightLimit.
     * When it crosses xRightLimit, x-coordinate is equal to xLeftLimit.
     */
    public void move() {
        double xCoordinate = this.getCoordinateX() + this.speedVector.getX();
        double yCoordinate = this.getCoordinateY() + this.speedVector.getY();

        if (this.teleporting) {
            if (xCoordinate < this.xLeftLimit) {
                xCoordinate = this.xRightLimit - (this.xLeftLimit - xCoordinate + X_LIMIT_OFFSET);
            } else if (this.xRightLimit < xCoordinate) {
                xCoordinate = this.xLeftLimit + (xCoordinate - this.xRightLimit + X_LIMIT_OFFSET);
            }
        }
        this.setCoordinates(xCoordinate, yCoordinate);
    }
}
