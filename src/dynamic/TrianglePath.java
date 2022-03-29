package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TrianglePath {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/trianglePath");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            int size = Integer.parseInt(br.readLine());
            int[][] triangle = new int[size][];
            for (int j = 0; j < size; j++) {
                triangle[j] = Arrays.stream(br.readLine().split("[ ]+")).mapToInt(Integer::parseInt).toArray();
            }
            MaxPathFinder finder = new MaxPathFinder(triangle);
            finder.printAnswer();
        }
    }

    static class MaxPathFinder {
        private int[][] triangle;

        private MaxPathFinder(int[][] triangle) {
            this.triangle = triangle;
        }

        private void printAnswer() {
            for (int i = 1, size = triangle.length; i < size; i++) {
                triangle[i][0] += triangle[i - 1][0];
                triangle[i][i] += triangle[i - 1][i - 1];

                for (int j = 1; j < i; j++) {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
            printMax();
        }

        private void printMax() {
            int max = 0;
            int size = triangle.length;
            for (int i = 0; i < size; i++) {
                max = Math.max(triangle[size-1][i], max);
            }
            System.out.println(max);
        }
    }
}
