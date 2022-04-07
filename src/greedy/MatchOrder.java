package greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MatchOrder {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/greedy/matchOrder");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            br.readLine();
            int[] enemyRating = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] allyRating = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            WinMaxFinder finder = new WinMaxFinder(enemyRating, allyRating);

            finder.printAnswer();
        }
    }

    private static class WinMaxFinder {
        private List<Integer> enemyRating;
        private List<Integer> allyRating;
        private int winMax = 0;

        private WinMaxFinder(int[] enemyRating, int[] allyRating) {
            this.enemyRating = Arrays.stream(enemyRating).boxed().collect(Collectors.toList());
            this.allyRating = Arrays.stream(allyRating).boxed().collect(Collectors.toList());
            Collections.sort(this.allyRating);
        }

        private void printAnswer() {
            while (!enemyRating.isEmpty()) {
                countWin();
            }
            System.out.println(winMax);
        }

        private void countWin() {
            int enemy = enemyRating.get(0);
            Iterator<Integer> iter = allyRating.iterator();

            while (iter.hasNext()) {
                if (iter.next() >= enemy) {
                    enemyRating.remove(0);
                    iter.remove();
                    winMax++;
                    return;
                }
            }
            enemyRating.remove(0);
            allyRating.remove(0);
        }
    }
}
