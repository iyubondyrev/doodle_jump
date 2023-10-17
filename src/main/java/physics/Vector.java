package physics;

public class Vector {
    private double x;
    private double y;

    /**
     * Contructor.
     * @param x x-value.
     * @param y y-value.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {}

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
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
