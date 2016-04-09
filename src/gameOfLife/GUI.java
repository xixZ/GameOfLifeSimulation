package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xi on 2016/4/8.
 */
public class GUI extends JFrame {
    private JPanel rootPanel;
    private JPanel controlPanel;
    private JButton runButton;
    private JButton pauseButton;
    private JButton clearButton;
    private JButton initializeButton;
    private JTextField percentageField;

    private GOLSimulation board;
    private BoardPanel boardPanel;

    public GUI(GOLSimulation board) {
        this.board = board;
        boardPanel = new BoardPanel(board);
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setSize(board.getCol() * board.getMultiplier() + 250, board.getRow() * board.getMultiplier() + 100);
        setLocationRelativeTo(null);
        setVisible(true);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.ipady = board.getRow() * board.getMultiplier();
        c.ipadx = board.getCol() * board.getMultiplier();
        c.gridx = 0;
        c.gridy = 0;
        rootPanel.add(boardPanel, c);
        addButtonListener();
        board.genRandomBoard(20);
    }

    void addButtonListener() {
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(true);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(false);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(false);
                boardPanel.clearBoard();
            }
        });

        initializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String percentageText = percentageField.getText();
                if (percentageText.isEmpty()) {
                    board.genRandomBoard(20);
                } else {
                    board.genRandomBoard(Double.parseDouble(percentageText));
                }
                boardPanel.repaint();
            }
        });
    }
}
