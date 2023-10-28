package game_engine;

import java.awt.Image;

/**
 * Base abstract class for game.
 * It contains thread in which game is run.
 * Method 'execGamePlot' must be overriden.
 */
public abstract class BaseGame extends BaseSurface {
    protected Window window;
    private Thread thread;

    /**
     * Constructor.
     * 
     * @param width  window width.
     * @param height window heigh.
     * @param image  background image.
     */
    public BaseGame(int width, int height, Image image) {
        super(image);
        this.window = new Window(width, height);
        this.window.add(this);
        this.returnFocus();
        
    }

    /**
     * Return focus to this window.
     */
    public void returnFocus() {
        this.setFocusable(true); // Make sure the panel is focusable
        this.requestFocusInWindow(); // Request focus on the panel
    }

    /**
     * Execute main game plot.
     * Method creates new thread and starts it.
     */
    public void exec() {
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                execGamePlot();
            }
        });
        this.thread.start();
    }

    /**
     * Execute main plot of game.
     */
    protected abstract void execGamePlot();
}
