import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClockSync {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/clockSync");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            ClockSyncer cs = new ClockSyncer(br.readLine());
            System.out.println(cs.getAnswer());
        }
    }

    static class ClockSyncer {
        private static final int[][] switches = {
                {0, 1, 2},
                {3, 7, 9, 11},
                {4, 10, 14, 15},
                {0, 4, 5, 6, 7},
                {6, 7, 8, 10, 12},
                {0, 2, 14, 15},
                {3, 14, 15},
                {4, 5, 7, 14, 15},
                {1, 2, 3, 4, 5},
                {3, 4, 5, 9, 13}
        };

        private int[] clocks = new int[16];
        private int answer = 31;

        private ClockSyncer(String str) {
            String[] arr = str.split(" ");
            for (int i = 0; i < 16; i++) {
                if (arr[i].equals("3")) {
                    clocks[i] = 1;
                } else if (arr[i].equals("6")) {
                    clocks[i] = 2;
                } else if (arr[i].equals("9")) {
                    clocks[i] = 3;
                }
            }
        }

        private int getAnswer() {
            push(0, 0);
            if (answer == 31) {
                answer = -1;
            }
            return answer;
        }

        private void push(int pushCount, int switchIndex) {
            if (isAllClockPointNorth()) {
                answer = Math.min(answer, pushCount);
            }
            if (switchIndex == switches.length) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                pushSwitch(i, switchIndex);
                push(pushCount + i, switchIndex + 1);
                pushSwitch(-i, switchIndex);
            }
        }

        private boolean isAllClockPointNorth() {
            for (int n : clocks) {
                if (n != 0) {
                    return false;
                }
            }
            return true;
        }

        private void pushSwitch(int count, int switchIndex){
            for(int clockIndex : switches[switchIndex]){
                pushClock(count, clockIndex);
            }
        }

        private void pushClock(int count, int clockIndex){
            clocks[clockIndex] += (count + 4);
            clocks[clockIndex] = clocks[clockIndex] % 4;
        }
    }
}
