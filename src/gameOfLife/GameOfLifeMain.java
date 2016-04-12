package gameOfLife;

import javax.swing.*;

/**
 * Created by xi on 2016/4/4.
 */
public class GameOfLifeMain implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(new GameOfLifeMain());
    }

    @Override
    public void run() {
        new GUI();
    }
}
