package gameOfLife;

/**
 * Created by xi on 2016/4/7.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BoardPanel extends JPanel implements ActionListener {
    Timer animation = new Timer(500, this);

    private GOLSimulation board;
    private boolean isActive;

    public BoardPanel(GOLSimulation board) {
        this.board = board;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isActive) {
                    board.getBoard()[e.getY() / board.getMultiplier() + 1][e.getX() / board.getMultiplier() + 1] = 1 - board.getBoard()[e.getY() / board.getMultiplier() + 1][e.getX() / board.getMultiplier() + 1];
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }

    public void setIsActive(boolean toSet) {
        isActive = toSet;
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
        if (isActive) {
            animation.restart();
        } else {
            animation.stop();
            repaint();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(animation)) {
            board.nextGeneration();
            repaint();
        }
    }
}