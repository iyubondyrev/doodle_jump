package game_engine;

/**
 * Base animation.
 * The following method can be overriden in order to customize animation:
 * - actionInit (taken before animation cycle).
 * - actionBegin (taken in the start of animation cycle).
 * - actionEnd (taken in the end of animation cycle).
 * - render (taken in the end of animation cycle to render animation).
 * - actionClose (taken after animation cycle).
*/
public abstract class BaseAnimation {
    private boolean runAnimation;

    private static final int NANOSECONDS_PER_SECOND = 1000000000;
    private static final int MILLISECONDS_PER_SECOND = 1000000;

    private static final int FPS = 60;
    private static final int TARGET_TIME = NANOSECONDS_PER_SECOND / FPS;

    /**
     * Get if animation is still running.
     * 
     * @return boolean (true - animation is running, falce - not).
     */
    protected boolean isRunAnimation() {
        return this.runAnimation;
    }

    /**
     * Stop animation.
     */
    public void stopAnimation() {
        this.runAnimation = false;
    }

    /**
     * Execute animation.
     */
    public void exec() {
        this.runAnimation = true;
        this.actionInit();

        while (runAnimation) {
            this.actionBegin();

            long startTime = System.nanoTime();
            long time = System.nanoTime() - startTime;

            if (time < TARGET_TIME) {
                long sleepTime = (TARGET_TIME - time) / MILLISECONDS_PER_SECOND;
                this.sleep(sleepTime);
            }

            this.actionEnd();
            this.render();
        }

        this.actionClose();
    }

    /**
     * Actions which are performed in the start of animation cycle.
     */
    protected void actionBegin() {
    }

    /**
     * Actions which are performed in the end of animation cycle.
     */
    protected void actionEnd() {
    }

    /**
     * Action which are performed before animation cycle.
     */
    protected void actionInit() {
    }

    /**
     * Action which are performed after animation cycle.
     */
    protected void actionClose() {
    }

    /**
     * Render image on window.
     */
    protected void render() {
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