package game_engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;

import game_engine.GamePanel;


/**
 * Class represents game panel on which animation is taken place.
 * Actions in animation are defined in abstract method preAction and postAction.
 */
public abstract class AnimationPanel extends GamePanel{
    private Thread thread;
    private boolean runAnimation;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    public AnimationPanel(Image image) {
        super(image);
    }

    /**
     * Get if animation is still running.
     * @return boolean (true - animation is running, falce - not).
     */
    protected boolean isRunAnimation() {
        return this.runAnimation;
    }

    /**
     * Interupt animation.
     */
    protected void interupt() {
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
                    preAction();

                    long startTime = System.nanoTime();
                    long time = System.nanoTime() - startTime;

                    if (time < TARGET_TIME) {
                        long sleepTime = (TARGET_TIME - time) / 1000000;
                        sleep(sleepTime);
                    }

                    postAction();
                }
            }
        });

        this.thread.start();
    }

    /**
     * Actions which are performed in the start of animation cycle.
     */
    protected abstract void preAction();

    /**
     * Actions which are performed in the end of animation cycle.
     */
    protected abstract void postAction();

    /**
     * Stop animation for specific time in order to work with defined FPS.
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