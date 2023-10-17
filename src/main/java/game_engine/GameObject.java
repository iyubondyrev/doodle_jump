package game_engine;

import physics.Vector;

import java.awt.Image;

/**
 * Class represents object with coordinates and image in game.
 */
public class GameObject extends GamePanel {
    private Vector speedVector;

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
        int x = this.getX() + (int) this.speedVector.getX();
        int y = this.getY() + (int) this.speedVector.getY();
        this.setLocation(x, y);
    }
}
