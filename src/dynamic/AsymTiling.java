package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AsymTiling {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/asymTiling");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        AnswerFinder finder = new AnswerFinder();

        for (int i = 0; i < caseCount; i++) {
            finder.printAnswer(Integer.parseInt(br.readLine()));
        }
    }

    private static class AnswerFinder {
        private static final int MOD = 1000000007;
        private int lastIndex = 1;
        private int[] cache = new int[101];

        private AnswerFinder() {
            cache[0] = 1;
            cache[1] = 1;
        }

        private void printAnswer(int n) {
            if (lastIndex < n) {
                fillCache(n);
            }
            System.out.println(getAsymmetryCount(n));
        }

        private void fillCache(int n) {
            for (int i = lastIndex + 1; i <= n; i++) {
                cache[i] = (cache[i - 2] + cache[i - 1]) % MOD;
            }
            lastIndex = n;
        }

        private int getAsymmetryCount(int n) {
            if (n % 2 == 1) {
                return (cache[n] - cache[n/2] + MOD) % MOD;
            }
            else {
                int ret = cache[n];
                ret = (ret - cache[n/2] + MOD) % MOD;
                ret = (ret - cache[n/2 - 1] + MOD) % MOD;
                return ret;
            }
        }
    }
}