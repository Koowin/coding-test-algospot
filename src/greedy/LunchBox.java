package greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LunchBox {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/greedy/lunchBox");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            int boxCount = Integer.parseInt(br.readLine());
            List<Box> boxList = new ArrayList<>(boxCount);

            int[] microwavingTimeList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] eatTimeList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < boxCount; j++) {
                boxList.add(new Box(microwavingTimeList[j], eatTimeList[j]));
            }
            System.out.println(countTotalTime(boxList));
        }
    }

    private int countTotalTime(List<Box> boxList) {
        Collections.sort(boxList);
        int max = 0;
        int eatStartTime = 0;

        for (Box b : boxList) {
            eatStartTime += b.microwavingTime;
            max = Math.max(max, eatStartTime + b.eatTime);
        }
        return max;
    }


    static class Box implements Comparable<Box> {
        private final int microwavingTime;
        private final int eatTime;

        public Box(int microwavingTime, int eatTime) {
            this.microwavingTime = microwavingTime;
            this.eatTime = eatTime;
        }

        @Override
        public int compareTo(Box o) {
            if (o.eatTime == eatTime) {
                return Integer.compare(o.microwavingTime, microwavingTime);
            }
            return Integer.compare(o.eatTime, eatTime);
        }
    }
}
