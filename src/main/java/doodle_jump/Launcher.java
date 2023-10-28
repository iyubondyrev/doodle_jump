package doodle_jump;

import javax.swing.SwingUtilities;

/**
 * Class which launches game.
 */
public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                game.exec();
            }
        });
    }
}
