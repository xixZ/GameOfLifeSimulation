package gameOfLife;

/**
 * Created by xi on 2016/4/7.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class BoardPanel extends JPanel {
    private GOLSimulation board;
    private boolean isActive;
    public BoardPanel(GOLSimulation board) {
        this.board = board;
        isActive = false;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isActive) {
                    board.getBoard()[e.getY() / board.getMultiplier() + 1][e.getX() / board.getMultiplier() + 1] = 1 - board.getBoard()[e.getY() / board.getMultiplier() + 1][e.getX() / board.getMultiplier() + 1];
                    repaint();
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }

    public void setIsActive(boolean toSet) {
        isActive = toSet;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public void clearBoard() {
        for (int i = 1; i <= board.getRow(); i++) {
            for (int j = 1; j <= board.getCol(); j++) {
                board.getBoard()[i][j] = 0;
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 1; i <= board.getRow(); i++) {
            for (int j = 1; j <= board.getCol(); j++) {
                g.setColor((board.getBoard()[i][j] == 1) ? Color.GREEN : Color.WHITE);
                g.fillRect((j - 1) * board.getMultiplier(), (i - 1) * board.getMultiplier(), board.getMultiplier() - 1, board.getMultiplier() - 1);

            }
        }
    }

    public void updateBoard() {
        board.nextGeneration();
        repaint();
    }

    public void setBoard(GOLSimulation board) {
        this.board = board;
    }
}