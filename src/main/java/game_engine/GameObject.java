package game_engine;

import physics.Vector;

import java.awt.Image;

/**
 * Class represents object with coordinates and image in game.
 */
public class GameObject extends GamePanel {
    private Vector speedVector;

    private static final double FALLING_Y_SPEED_LIMIT = 0;

    /**
     * Constructor.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left cordner.
     * @param image object image.
     */
    public GameObject(int x, int y, Image image) {
        super(x, y, image);
        this.speedVector = new Vector();
    }

    /**
     * Get speed vector.
     * @return speed vector.
     */
    public Vector getSpeedVector() {
        return this.speedVector;
    }

    /**
     * Define if object falls.
     * @return boolean (true - object is falling, false - not).
     */
    public boolean isFalling() {
        return this.speedVector.getY() > FALLING_Y_SPEED_LIMIT;
    }

    /**
     * Set speed vector.
     * @param x x-value of speed vector.
     * @param y y-value of speed vector.
     */
    public void setSpeedVector(double x, double y) {
        this.setSpeedVector(new Vector(x, y));
    }

    /**
     * Set speed vector.
     * @param vector speed vector.
     */
    public void setSpeedVector(Vector vector) {
        this.speedVector = vector;
    }

    /**
     * Change speed vector by acceleration Vector.
     * @param acceleration acceleration Vector,
     */
    public void addAcceleration(Vector acceleration) {
        this.speedVector.add(acceleration);
    }

    /**
     * Move object.
     * Object is moved with the direction and speed of speed vector.
     */
    public void move() {
        this.setCoordinates(
            this.getCoordinateX() + this.speedVector.getX(),
            this.getCoordinateY() + this.speedVector.getY());
    }
}
