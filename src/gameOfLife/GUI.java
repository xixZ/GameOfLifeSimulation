package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xi on 2016/4/8.
 */
public class GUI extends JFrame implements ActionListener {
    Timer tick = new Timer(500, this);

    private JPanel rootPanel;
    private JPanel controlPanel;
    private JButton runButton;
    private JButton pauseButton;
    private JButton clearButton;
    private JButton initializeButton;
    private JTextField percentageField;
    private JLabel tickCount;
    private JButton fastForwardButton;
    private JButton jumpToButton;
    private JTextField tickValue;

    private GOLSimulation board;
    private BoardPanel boardPanel;
    private Integer curTime;
    public GUI(GOLSimulation board) {
        //  tick.start();
        this.board = board;
        curTime = 0;
        boardPanel = new BoardPanel(board);
        setContentPane(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setSize(board.getCol() * board.getMultiplier() + 320, board.getRow() * board.getMultiplier() + 100);
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

    private void addButtonListener() {
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(true);
                tick.setDelay(500);
                tick.start();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(false);
                tick.stop();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.setIsActive(false);
                boardPanel.clearBoard();
                resetTime();
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
                resetTime();
            }
        });

        fastForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick.setDelay(100);
                boardPanel.setIsActive(true);
                tick.start();
            }
        });


        jumpToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = tickValue.getText();
                if (!val.isEmpty()) {
                    int toTickVal = Integer.parseInt(val);
                    tick.stop();
                    while (curTime < toTickVal) {
                        boardPanel.updateBoard();
                        advanceTime();
                    }
                    tick.start();
                }
            }
        });
    }

    public void advanceTime() {
        curTime++;
        tickCount.setText("Current Time: " + curTime.toString());
    }

    public void resetTime() {
        curTime = 0;
        tickCount.setText("Current Time: " + curTime.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boardPanel.getIsActive()) {
            boardPanel.updateBoard();
            advanceTime();
        }
    }

}
