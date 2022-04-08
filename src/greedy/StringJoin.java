package greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringJoin {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/greedy/stringJoin");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            br.readLine();
            int[] lengthArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            AnswerFinder finder = new AnswerFinder(lengthArray);
            finder.printAnswer();
        }
    }

    static class AnswerFinder {
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        private AnswerFinder(int[] lengthArray) {
            for (int i : lengthArray) {
                priorityQueue.add(i);
            }
        }

        private void printAnswer() {
            int sum = 0;
            while (priorityQueue.size() > 1) {
                int n = priorityQueue.poll() + priorityQueue.poll();
                priorityQueue.offer(n);
                sum += n;
            }
            System.out.println(sum);
        }
    }
}
