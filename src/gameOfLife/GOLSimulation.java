package gameOfLife;

import java.util.Random;

/**
 * Created by xi on 2016/4/4.
 */
public class GOLSimulation {
    private final int row, col;
    private int[][] board;
    GOLSimulation(int row, int col) {
        this.row = row;
        this.col = col;
        board = new int[row+2][col+2];
        initialize();
    }
    private void initialize(){
        for (int i = 0; i < row + 2; i ++) {
            board[i][0] = 0;
            board[i][col+1] = 0;
        }
        for (int i = 0; i < col + 2; i ++) {
            board[0][i] = 0;
            board[row+1][i] = 0;
        }
        Random random = new Random(System.currentTimeMillis());
        for (int i = 1; i <= row; i ++) {
            for (int j = 1; j <= col; j ++) {
                board[i][j] = random.nextInt(2);
            }
        }
    }
    public void start() throws InterruptedException {
        boolean changed = true;
        int[][] newBoard = new int[row+2][col+2];
        for (int i = 0; i < row + 2; i ++) {
            for (int j = 0; j < col + 2; j ++) {
                newBoard[i][j] = 0;
            }
        }
        while(changed) {
            int neighbors;
            printBoard();
            changed = false;
            for (int i = 1; i <= row; i ++) {
                for (int j = 1; j <= col; j ++) {
                    neighbors = countNeighbor(i, j);
                    if (neighbors <= 1 || neighbors >= 4) {
                        newBoard[i][j] = 0;
                        changed = changed || (board[i][j] == 1);
                    } else if(neighbors == 2) {
                        newBoard[i][j] = board[i][j];
                    } else if(neighbors == 3) {
                        newBoard[i][j] = 1;
                        changed = changed || (board[i][j] == 0);
                    }
                }
            }
            int[][] tmp = newBoard;
            newBoard = board;
            board = tmp;
            Thread.sleep(1000);
        }
    }
    private void printBoard() {
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
}
