package doodle_jump;

import doodle_jump.Game;

import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
                game.exec();
            }
        });
    }
    
}
