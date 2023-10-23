package game_engine;

import game_engine.BaseElement;
import game_engine.Window;
import java.awt.Image;

public abstract class BaseGame extends BaseElement2 {
    private Thread thread;
    private boolean runAnimation;
    private Window window;

    private static final int NANOSECONDS_PER_SECOND = 1000000000;
    private static final int MILLISECONDS_PER_SECOND = 1000000;

    private static final int FPS = 60;
    private static final int TARGET_TIME = NANOSECONDS_PER_SECOND / FPS;

    public BaseGame(int width, int height, Image image) {
        super(image);
        this.window = new Window(width, height);
        this.window.add(this);

        this.setFocusable(true); // Make sure the panel is focusable
        this.requestFocusInWindow(); // Request focus on the panel
    }

    protected Window getWindow() {
        return this.window;
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

        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runAnimation) {
                    actionBegin();

                    long startTime = System.nanoTime();
                    long time = System.nanoTime() - startTime;

                    if (time < TARGET_TIME) {
                        long sleepTime = (TARGET_TIME - time) / MILLISECONDS_PER_SECOND;
                        sleep(sleepTime);
                    }

                    actionEnd();
                    render();
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
     * Render image on window.
     */
    protected void render() {
        // this.window.validate();
    }

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
