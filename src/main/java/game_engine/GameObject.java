package game_engine;

import physics.Vector;

import java.awt.Image;


public class GameObject extends GamePanel {
    private Vector speedVector;

    /**
     * 
     * @param x
     * @param y
     * @param image
     */
    public GameObject(int x, int y, Image image) {
        super(x, y, image);
        this.speedVector = new Vector();
    }

    public Vector getSpeedVector() {
        return this.speedVector;
    }

    public void setSpeedVector(double x, double y) {
        this.speedVector = new Vector(x, y);
    }

    public void setSpeedVector(Vector vector) {
        this.speedVector = vector;
    }

    public void addAcceleration(Vector acceleration) {
        this.speedVector.add(acceleration);
    }

    public void move() {
        int x = this.getX() + (int) this.speedVector.getX();
        int y = this.getY() + (int) this.speedVector.getY();
        this.setLocation(x, y);
    }
}
