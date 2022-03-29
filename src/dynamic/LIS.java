package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LIS {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/LIS");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            br.readLine();
            int[] list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            AnswerFinder finder = new AnswerFinder(list);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        private final int[] list;
        private int[] cache;

        private AnswerFinder(int[] list) {
            this.list = list;
            cache = new int[list.length];
        }

        private void printAnswer() {
            int answer = 0;
            for (int i = 0; i < list.length; i++) {
                answer = Math.max(answer, findMaxSequenceLength(i));
            }
            System.out.println(answer);
        }

        private int findMaxSequenceLength(int i) {
            if (cache[i] != 0) {
                return cache[i];
            }
            int ret = 1;
            int nowNumber = list[i];
            for (int j = i + 1; j < list.length; j++) {
                if (nowNumber < list[j]) {
                    ret = Math.max(ret, findMaxSequenceLength(j) + 1);
                }
            }
            cache[i] = ret;
            return ret;
        }

    }
}
