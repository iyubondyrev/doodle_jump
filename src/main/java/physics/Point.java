package physics;

/**
 * 2D-point.
 * Type of coordinates can be only subclasses of Number: Double, Integer, etc.
 */
public class Point<T extends Number> {
    private T x;
    private T y;

    /**
     * Constructor.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public Point(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor to create with coordinates (0, 0).
     */
    public Point() {}

    /**
     * Set coordinates.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void setCoordinates(T x, T y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set x-coordinate.
     * @param x x-coordinate.
     */
    public void setX(T x) {
        this.x = x;
    }

    /**
     * Set y-coordinate.
     * @param y y-coordinate.
     */
    public void setY(T y) {
        this.y = y;
    }

    /**
     * Get x-coordinate.
     * @return x-coordinate.
     */
    public T getX() {
        return this.x;
    }

    /**
     * Get y-coordinate.
     * @return y-coordinate.
     */
    public T getY() {
        return this.y;
    }
}
