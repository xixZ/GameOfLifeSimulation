package gameOfLife;

import java.util.Random;

/**
 * Created by xi on 2016/4/4.
 */
public class GOLSimulation {
    private final int row, col, multiplier = 10;
    private int[][] board;
    private int[][] nextBoard;
    GOLSimulation(int row, int col) {
        this.row = row;
        this.col = col;
        board = new int[row+2][col+2];
        nextBoard = new int[row + 2][col + 2];
        initialize();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMultiplier() {
        return multiplier;
    }
    private void initialize(){
        for (int i = 0; i < row + 2; i ++) {
            board[i][0] = 0;
            board[i][col+1] = 0;
            nextBoard[i][0] = 0;
            nextBoard[i][col + 1] = 0;
        }
        for (int i = 0; i < col + 2; i ++) {
            board[0][i] = 0;
            board[row+1][i] = 0;
            nextBoard[0][i] = 0;
            nextBoard[row + 1][i] = 0;
        }
        //genRandomBoard();
    }

    public void nextGeneration() {
        int countNeighbor;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                countNeighbor = countNeighbor(i, j);
                if (countNeighbor <= 1 || countNeighbor >= 4) {
                    nextBoard[i][j] = 0;
                } else if (countNeighbor == 2) {
                    nextBoard[i][j] = board[i][j];
                } else if (countNeighbor == 3) {
                    nextBoard[i][j] = 1;
                }
            }
        }
        int[][] tmp = nextBoard;
        nextBoard = board;
        board = tmp;
        //   printBoard(board, row, col);
    }

    private void printBoard(int[][] board, int row, int col) {
        for(int i = 1; i <= row; i ++) {
            for(int j = 1; j <= col; j ++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private int countNeighbor(int rowIndex, int colIndex) {
        int count = 0;
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1; j ++) {
                count += board[rowIndex+i][colIndex+j];
            }
        }
        count -= board[rowIndex][colIndex];
        return count;
    }

    public void genRandomBoard(double percentage) {
        Random random = new Random(System.currentTimeMillis());
        int next;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                next = random.nextInt(100);
                if (next < percentage)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
                nextBoard[i][j] = 0;
            }
        }
    }
}
