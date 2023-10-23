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
     * Get speed vector.
     * @return speed vector.
     */
    public Vector getSpeedVector() {
        return this.speedVector;
    }

    /**
     * Set boost vector
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
     * @return boost vector.
     */
    public Vector getBoostVector() {
        return this.boostVector;
    }

    public void addToBoostVector(Vector vector) {
        this.boostVector.add(vector);
    }

    public void subtractFromBoostVector(Vector vector) {
        this.boostVector.subtract(vector);
    }

    public void multiplyBoostVector(double value) {
        this.boostVector.multiply(value);
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
     * Subtract from speed-vector.
     * Method receives vector.
     * @param substractingVector subtracting vector.
     */
    public void subtractFromSpeedVector(Vector substractingVector) {
        this.speedVector.subtract(substractingVector);
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
     */
    public void move() {
        double x = this.getCoordinateX() + this.getSpeedVector().getX();
        double y = this.getCoordinateY() + this.getSpeedVector().getY();
        this.setCoordinates(x, y);
    }

    @Override
    protected void actionBegin() {
        move();
    }

    @Override
    protected void actionEnd() {}

    /**
     * Get rectangle which cover the whole object.
     * Rectangle is required to calculate collision.
     * @return rectangle which is used to calculate collision.
     */
    public Rectangle getRectangle() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
