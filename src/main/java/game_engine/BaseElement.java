package game_engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public abstract class BaseElement extends JPanel {
    protected Image image;
    protected Point2D.Double coordinates;
    protected ArrayList<BaseElement> animatedElements;

    private Thread thread;
    private boolean runAnimation;

    private final int NANOSECONDS_PER_SECOND = 1000000000;
    private final int MILLISECONDS_PER_SECOND = 1000000;

    private final int FPS = 60;
    private final int TARGET_TIME = NANOSECONDS_PER_SECOND / FPS;

    /**
     * Constructor.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     * @param image image.
     */
    public BaseElement(int x, int y, Image image) {
        super(null);
        this.setOpaque(false);

        this.image = image;
        this.setSize(this.image.getWidth(null), this.image.getHeight(null));

        this.coordinates = new Point2D.Double();
        this.setCoordinates(x, y);

        this.animatedElements = new ArrayList<>();
    }

    /**
     * Constructor to create with coordinates (0, 0).
     * @param image image.
     */
    public BaseElement(Image image) {
        this(0, 0, image);
    }
    
    /**
     * Set image.
     * @param image image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Get image.
     * @return image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Set coordinates in floating-point format (double).
     * JPanel.setLocation gets coordinates in integer format after casting from double to int.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(double x, double y) {
        this.setLocation((int) x, (int) y);
        this.coordinates.setLocation(x, y);
    }

    /**
     * Set coordinates in integer format (int).
     * JPanel.setLocation gets coordinates in integer format without casting.
     * @param x x-coordinate of top-left corner.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinates(int x, int y) {
        this.setLocation(x, y);
        this.coordinates.setLocation((double) x, (double) y);
    }

    /**
     * Set x-coordinate in floating-point format (double).
     * @param x x-coordinate of top-left corner.
     */
    public void setCoordinateX(double x) {
        this.setCoordinates(x, this.coordinates.getY());
    }

    /**
     * Set x-coordinate in integer format (int).
     * WARMING: y-coordinate will be rounded down automatically in a result of casting to int.
     * @param x x-coordinate of top-left corner.
     */
    public void setCoordinateX(int x) {
        this.setCoordinates(x, this.getY());
    }

    /**
     * Set y-coordinates in floating-point format (double).
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinateY(double y) {
        this.setCoordinates(this.coordinates.getX(), y);
    }

    /**
     * Set y-coordinates in integer format (int).
     * WARMING: x-coordinate will be rounded down automatically in a result of casting to int.
     * @param y y-coordinate of top-left corner.
     */
    public void setCoordinateY(int y) {
        this.setCoordinates(this.getX(), y);
    }

    /**
     * Get x-coordinate.
     * @return x-coordinate.
     */
    public double getCoordinateX() {
        return this.coordinates.getX();
    }

    /**
     * Get y-coordinate.
     * @return y-coordinate.
     */
    public double getCoordinateY() {
        return this.coordinates.getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(
                this.image, 0, 0,
                this.image.getWidth(null),
                this.image.getHeight(null),
                this);
        }
    }

    /**
     * Get if animation is still running.
     * 
     * @return boolean (true - animation is running, falce - not).
     */
    protected boolean isRunAnimation() {
        return this.runAnimation;
    }

    /**
     * Interupt animation.
     */
    protected void stopAnimation() {
        this.runAnimation = false;
    }

    /**
     * Run animation by creating animation thread and starting it.
     * Subclasses can change plot of animation by defining abstract
     * methods preAction and postAction.
     */
    protected void runAnimation() {
        this.runAnimation = true;

        for (BaseElement element: animatedElements) {
            element.runAnimation();
        }

        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runAnimation) {
                    actionBegin();

                    long startTime = System.nanoTime();
                    //long time = System.nanoTime() - startTime;

                    /* if (time < TARGET_TIME) {
                        long sleepTime = (TARGET_TIME - time) / MILLISECONDS_PER_SECOND;
                        sleep(sleepTime);
                    } */
                    sleep(10);

                    actionEnd();
                }
            }
        });

        this.thread.start();
    }

    /**
     * Actions which are performed in the start of animation cycle.
     */
    protected abstract void actionBegin();

    /**
     * Actions which are performed in the end of animation cycle.
     */
    protected abstract void actionEnd();

    /**
     * Stop animation for specific time in order to work with defined FPS.
     * 
     * @param time time which animation is stopped.
     */
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}