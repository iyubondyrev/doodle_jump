package game_engine;


import java.awt.Image;
import java.awt.Rectangle;
import physics.Vector;

/**
 * Class represents object with coordinates and image in game.
 */
public abstract class GameObject extends BaseElement {
    protected Vector speedVector;
    protected Vector boostVector;

    private static final double FALLING_SPEED_THRESHOLD = 0;
    

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
        this.speedVector = new Vector();
        this.boostVector = new Vector();
    }

    public GameObject(int x, int y, Image image) {
        this(x, y, image, false, 0, 0);
    }

    public GameObject(Image image) {
        this(0, 0, image, false, 0, 0);
    }

    /**
     * Get speed vector.
     * @return speed vector.
     */
    public Vector getSpeedVector() {
        return this.speedVector;
    }

    /**
     * Get boost vector.
     * @return boost vector.
     */
    public Vector getBoostVector() {
        return this.speedVector;
    }

    public void setBoostVector(Vector vector) {
        if (vector == null) {
            vector = new Vector(0., 0.);
        }
        this.boostVector.setLocation(vector);
    }

    public void addToBoostVector(Vector vector) {
        this.boostVector.add(vector);
    }

    public void subFromBoostVector(Vector vector) {
        this.boostVector.subtract(vector);
    }

    public void multiplyBoostVector(double value) {
        this.boostVector.multiply(value);
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
        if (vector == null) {
            vector = new Vector(0., 0.);
        }
        this.speedVector.setLocation(vector);
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
        double x = this.getCoordinateX() + this.getSpeedVector().getX();
        double y = this.getCoordinateY() + this.getSpeedVector().getY();

        this.setCoordinates(x, y);

        this.speedVector.add(this.boostVector);
    }

    @Override
    protected void actionBegin() {
        move();
    }

    @Override
    protected void actionEnd() {}

    public abstract Rectangle getRectangle();
}
