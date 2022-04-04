package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TrianglePathCount {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/trianglePathCount");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            int size = Integer.parseInt(br.readLine());
            int[][] triangle = new int[size][];
            for (int j = 0; j < size; j++) {
                triangle[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            AnswerFinder finder = new AnswerFinder(triangle);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private final int[][] triangle;
        private int[][] valueCache;
        private int[][] pathCountCache;

        private AnswerFinder(int[][] triangle) {
            this.triangle = triangle;

            valueCache = new int[triangle.length][];
            pathCountCache = new int[triangle.length][];
            for (int i = 0; i < triangle.length; i++) {
                valueCache[i] = new int[i + 1];
                pathCountCache[i] = new int[i + 1];
            }
        }

        private void printAnswer() {
            countPath(0, 0);
            System.out.println(pathCountCache[0][0]);
        }

        private void countPath(int i, int j) {
            if (pathCountCache[i][j] != 0) {
                return;
            }

            if (i + 1 == triangle.length) {
                valueCache[i][j] = triangle[i][j];
                pathCountCache[i][j] = 1;
                return;
            }

            countPath(i + 1, j);
            countPath(i + 1, j + 1);

            if (valueCache[i + 1][j] == valueCache[i + 1][j + 1]) {
                pathCountCache[i][j] = pathCountCache[i + 1][j] + pathCountCache[i + 1][j + 1];
                valueCache[i][j] = valueCache[i + 1][j] + triangle[i][j];
            } else if (valueCache[i + 1][j] > valueCache[i + 1][j + 1]) {
                pathCountCache[i][j] = pathCountCache[i + 1][j];
                valueCache[i][j] = valueCache[i + 1][j] + triangle[i][j];
            } else {
                pathCountCache[i][j] = pathCountCache[i + 1][j + 1];
                valueCache[i][j] = valueCache[i + 1][j + 1] + triangle[i][j];
            }
        }
    }
}