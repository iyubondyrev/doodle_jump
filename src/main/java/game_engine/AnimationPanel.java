package game_engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;

import game_engine.GamePanel;

public abstract class AnimationPanel extends GamePanel{
    private Thread thread;
    private boolean runAnimation;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    public AnimationPanel(Image image) {
        super(image);
    }

    protected boolean isRunAnimation() {
        return this.runAnimation;
    }

    protected void interupt() {
        this.runAnimation = false;
    }

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

    protected abstract void preAction();
    protected abstract void postAction();

    private void sleep(long speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
