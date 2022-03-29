package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PI {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/PI");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String str = br.readLine();
            AnswerFinder finder = new AnswerFinder(str);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private final String str;
        private int[][] cache;

        private AnswerFinder(String str) {
            this.str = str;
            cache = new int[3][str.length() - 2];
        }

        private void printAnswer() {
            System.out.println(getMinDifficulty(0));
        }

        private int getMinDifficulty(int i) {
            int remainLength = str.length() - i;

            if (remainLength < 6) {
                return getDifficulty(i, remainLength);
            }

            int min = Integer.MAX_VALUE;
            int limit = Math.min(remainLength - 2, 6);

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
            char c = str.charAt(start);
            for (int i = 1; i < length; i++) {
                if (c != str.charAt(start + i)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDifficulty2(int start, int length) {
            int d = str.charAt(start+1) - str.charAt(start);
            if (d != -1 && d != 1) {
                return false;
            }
            return isDifficulty5(start, length);
        }

        private boolean isDifficulty4(int start, int length) {
            char[] c = new char[2];
            c[0] = str.charAt(start);
            c[1] = str.charAt(start + 1);

            for (int i = 2; i < length; i++) {
                if (str.charAt(start + i) != c[i % 2]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isDifficulty5(int start, int length) {
            int d = str.charAt(start+1) - str.charAt(start);
            for (int i = 2; i < length; i++) {
                if (str.charAt(i) - str.charAt(i - 1) != d) {
                    return false;
                }
            }
            return true;
        }
    }
}
