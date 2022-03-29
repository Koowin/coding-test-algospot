package divideconquer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FanMeeting {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/divideconquer/fanMeeting");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String members = br.readLine();
            String fans = br.readLine();

            HugCounter hc = new HugCounter(members, fans);
            System.out.println(hc.getAnswer());
        }
    }

    static class HugCounter {
        private boolean[] members;
        private boolean[] fans;

        private HugCounter(String members, String fans) {
            this.members = new boolean[members.length()];
            this.fans = new boolean[fans.length()];

            for (int i = 0; i < members.length(); i++) {
                if (members.charAt(i) == 'M') {
                    this.members[i] = true;
                }
            }
            for (int i = 0; i < fans.length(); i++) {
                if (fans.charAt(i) == 'M') {
                    this.fans[i] = true;
                }
            }
        }

        private int getAnswer() {
            int count = 0;
            int loopSize = fans.length - members.length + 1;
            for (int i = 0; i < loopSize; i++) {
                if (isAllHug(i)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isAllHug(int startIndex) {
            for (int i = 0; i < members.length; i++) {
                if (members[i] & fans[startIndex++]) {
                    return false;
                }
            }
            return true;
        }
    }
}
