package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PI_failed {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/PI");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            AnswerFinder finder = new AnswerFinder(arr);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private final int[] list;
        private int[][] cache;

        private AnswerFinder(int[] list) {
            this.list = list;
            cache = new int[3][list.length - 2];
        }

        private void printAnswer() {
            System.out.println(getMinDifficulty(0));
        }

        private int getMinDifficulty(int i) {
            if (list.length - i < 6) {
                return getDifficulty(i, list.length - i);
            }

            int min = Integer.MAX_VALUE;
            int limit = Math.min(list.length - i - 2, 6);

            for (int j = 3; j < limit; j++) {
                min = Math.min(min, getMinDifficulty(i + j) + getDifficulty(i, j));
            }
            return min;
        }

        private int getDifficulty(int start, int length) {
            if (cache[length - 3][start] != 0) {
                return cache[length - 3][start];
            }

            if (isDifficulty1(start, length)) {
                cache[length - 3][start] = 1;
                return 1;
            } else if (isDifficulty2(start, length)) {
                cache[length - 3][start] = 2;
                return 2;
            } else if (isDifficulty4(start, length)) {
                cache[length - 3][start] = 4;
                return 4;
            } else if (isDifficulty5(start, length)) {
                cache[length - 3][start] = 5;
                return 5;
            } else {
                cache[length - 3][start] = 10;
                return 10;
            }
        }

        private boolean isDifficulty1(int start, int length) {
            int num = list[start];
            for (int i = 1; i < length; i++) {
                if (num != list[start + i]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDifficulty2(int start, int length) {
            int num = list[start];
            for (int i = 1; i < length; i++) {
                if (num + i != list[start + i] && num - i != list[start + i]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDifficulty4(int start, int length) {
            int evenNum = list[start];
            int oddNum = list[start + 1];

            for (int i = 2; i < length; i++) {
                if (i % 2 == 0) {
                    if (list[start + i] != evenNum) {
                        return false;
                    }
                } else if (list[start + i] != oddNum) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDifficulty5(int start, int length) {
            int d = list[start + 1] - list[start];
            int num = list[start + 1];

            for (int i = 2; i < length; i++) {
                num += d;
                if (num != list[start + i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
