package bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BoardCover {
    public static void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/bruteforce/boardCover");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String[] rowColStrArr = br.readLine().split(" ");
            int row = Integer.parseInt(rowColStrArr[0]);
            int column = Integer.parseInt(rowColStrArr[1]);
            String[] board = new String[row];
            for (int j = 0; j < row; j++) {
                board[j] = br.readLine();
            }

            BlockSetter bs = new BlockSetter(row, column, board);
            System.out.println(bs.getAnswer());
        }
    }

    static class BlockSetter {
        private static final int[][][] coverType = {
                {{0, 0}, {1, 0}, {1, -1}},
                {{0, 0}, {1, 0}, {1, 1}},
                {{0, 0}, {0, 1}, {1, 0}},
                {{0, 0}, {0, 1}, {1, 1}}
        };
        private boolean[][] board;
        private int row = 0;
        private int col = -1;
        private final boolean canSolve;

        private BlockSetter(int row, int col, String[] strBoard) {
            board = new boolean[row][col];

            int emptyBlockCount = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (strBoard[i].charAt(j) == '#') {
                        board[i][j] = true;
                    } else {
                        emptyBlockCount++;
                    }
                }
            }

            if (emptyBlockCount % 3 == 0) {
                canSolve = true;
            } else {
                canSolve = false;
            }
        }

        private int getAnswer() {
            if (canSolve) {
                return cover();
            }
            return 0;
        }

        private int cover() {
            if (!findNextEmptyPosition()) {
                return 1;
            }
            int ret = 0;
            int oldRow = row;
            int oldCol = col;
            for (int type = 0; type < coverType.length; type++) {
                if (canSetBlock(oldRow, oldCol, type)) {
                    setBlock(oldRow, oldCol, type, true);
                    ret += cover();
                    setBlock(oldRow, oldCol, type, false);
                }
            }
            return ret;
        }

        private boolean findNextEmptyPosition() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (!board[i][j]) {
                        row = i;
                        col = j;
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean canSetBlock(int oldRow, int oldCol, int type) {
            for (int[] rc : coverType[type]) {
                int newRow = oldRow + rc[0];
                int newCol = oldCol + rc[1];
                if (isOutOfRange(newRow, newCol) || board[newRow][newCol]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isOutOfRange(int r, int c) {
            return r < 0 || c < 0 || r >= board.length || c >= board[0].length;
        }

        private void setBlock(int oldRow, int oldCol, int type, boolean value) {
            for (int[] rc : coverType[type]) {
                int newRow = oldRow + rc[0];
                int newCol = oldCol + rc[1];
                board[newRow][newCol] = value;
            }
        }
    }
}
