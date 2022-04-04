package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Poly {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/poly");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            AnswerFinder finder = new AnswerFinder(Integer.parseInt(br.readLine()));
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private static final int MOD = 10_000_000;
        private final int N;
        private int[][] cache;

        private AnswerFinder(int n) {
            this.N = n;
            cache = new int[n + 1][n + 1];
            for (int i = 1; i < n + 1; i++) {
                cache[i][i] = 1;
            }
        }

        private void printAnswer() {
            countMonotone();
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += cache[N][i];
                sum %= MOD;
            }
            System.out.println(sum);
        }

        private void countMonotone() {
            for (int i = 2; i <= N; i++) {
                for (int j = 1; j < i; j++) {
                    int m = i - j;
                    for (int k = 1; k <= m; k++) {
                        cache[i][j] += cache[m][k] * (k + j - 1);
                        cache[i][j] %= MOD;
                    }
                }
            }
        }
    }
}
