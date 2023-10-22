package physics;

import java.awt.geom.Point2D;

/**
 * 2D-Vector.
 */
public class Vector extends Point2D.Double {
    /**
     * Constructor.
     * @param x x-value.
     * @param y y-value.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor to create with values (0, 0).
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Add vector.
     * @param addingVector vector which is added to current vector.
     */
    public void add(Vector addingVector) {
        this.x = this.x + addingVector.getX();
        this.y = this.y + addingVector.getY();
    }

    /**
     * Subtract vector.
     * @param subtractingVector vector which is subtracted from current vector.
     */
    public void subtract(Vector subtractingVector) {
        this.x = this.x - subtractingVector.getX();
        this.y = this.y - subtractingVector.getY();
    }

    /**
     * Multiply vector on scalar value.
     * @param value scalar value.
     */
    public void multiply(double value) {
        this.x *= value;
        this.y *= value;
    }
}