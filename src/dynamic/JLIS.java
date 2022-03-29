package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JLIS {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/JLIS");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            br.readLine();
            int[] list1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] list2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }
    }

    static class AnswerFinder {
        private final int[] list1;
        private final int[] list2;
        private int[][] cache;

        private AnswerFinder(int[] list1, int[] list2) {
            this.list1 = list1;
            this.list2 = list2;
            cache = new int[list1.length][list2.length];
        }

        private void printAnswer() {

        }

        private int findMaxSequenceLength(int i, int j) {
            if (cache[i][j] != 0) {
                return cache[i][j];
            }
            return 0;
        }
    }
}
