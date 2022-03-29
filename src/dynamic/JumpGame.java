package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JumpGame {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/jumpGame");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            int size = Integer.parseInt(br.readLine());
            int[][] board = new int[size][size];
            for (int j = 0; j < size; j++) {
                board[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            PathFinder pf = new PathFinder(board);
            System.out.println(pf.getAnswer());
        }
    }

    static class PathFinder {
        private final int[][] board;
        private final int size;
        private boolean[][] checked;

        private PathFinder(int[][] board) {
            this.board = board;
            size = board.length;
            checked = new boolean[size][size];
        }

        private String getAnswer() {
            jump(0, 0);
            if(checked[size-1][size-1]){
                return "YES";
            }
            else{
                return "NO";
            }
        }

        private void jump(int row, int column) {
            checked[row][column] = true;
            if (row + board[row][column] < size) {
                if (!checked[row + board[row][column]][column]) {
                    jump(row + board[row][column], column);
                }
            }

            if (column + board[row][column] < size) {
                if (!checked[row][column + board[row][column]]) {
                    jump(row, column + board[row][column]);
                }
            }
        }
    }
}