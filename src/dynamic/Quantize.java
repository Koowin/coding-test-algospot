package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Quantize {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/quantize");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            String[] arr = br.readLine().split(" ");
            int[] list = Arrays.stream(br.readLine().split("[ ]+")).mapToInt(Integer::parseInt).toArray();
            AnswerFinder finder = new AnswerFinder(Integer.parseInt(arr[1]), list);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private final int groupCount;
        private final int[] list;
        private int[] sumList;
        private int[][] cache;

        private AnswerFinder(int numberCount, int[] l) {
            this.groupCount = numberCount;
            sumList = new int[l.length + 1];

            list = l;
            Arrays.sort(list);

            for (int i = 1; i < sumList.length; i++) {
                sumList[i] = sumList[i - 1] + l[i - 1];
            }

            cache = new int[list.length][11];
        }

        private void printAnswer() {
            System.out.println(quantize(0, groupCount));
        }

        private int quantize(int from, int parts) {
            if (cache[from][parts] != 0) {
                return cache[from][parts];
            }

            if (parts == 1) {
                return getMinError(from, list.length);
            }

            int minError = Integer.MAX_VALUE;
            for (int i = from; i < list.length; i++) {
                int thisError = getMinError(from, i) + quantize(i, parts - 1);
                minError = Math.min(minError, thisError);
            }

            cache[from][parts] = minError;
            return minError;
        }

        private int getMinError(int from, int to) {
            if (to - from <= 1) {
                return 0;
            }

            int count = to - from;
            int average = (int) Math.round((double)(sumList[to] - sumList[from]) / count);

            int error = 0;
            for (int i = from; i < to; i++) {
                int diff = list[i] - average;
                error += diff * diff;
            }
            return error;
        }
    }
}
