package dynamic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class WildCard {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/dynamic/wildCard");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            StringMatcher sm = new StringMatcher(br.readLine());
            int nameCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < nameCount; j++) {
                sm.addString(br.readLine());
            }
            sm.printAnswer();
        }
    }

    static class StringMatcher {
        private final String wildCard;
        private String nowStr;
        int[][] cache;

        private List<String> matchPool = new ArrayList<>();

        private StringMatcher(String wildCard) {
            this.wildCard = wildCard;
        }

        private void addString(String str) {
            cache = new int[wildCard.length() + 1][str.length() + 1];
            nowStr = str;
            if (isMatch(0, 0)) {
                matchPool.add(str);
            }
        }

        private boolean isMatch(int w, int s) {
            if (cache[w][s] != 0) {
                if (cache[w][s] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            if (w < wildCard.length() && s < nowStr.length() && (wildCard.charAt(w) == '?' || wildCard.charAt(w) == nowStr.charAt(s))) {
                boolean ret = isMatch(w + 1, s + 1);
                if (ret) {
                    cache[w][s] = 1;
                } else {
                    cache[w][s] = -1;
                }
                return ret;
            }
            if (w == wildCard.length()) {
                if (s == nowStr.length()) {
                    cache[w][s] = 1;
                    return true;
                }
                else {
                    cache[w][s] = -1;
                    return false;
                }
            }
            if (wildCard.charAt(w) == '*') {
                for (int i = s; i <= nowStr.length(); i++) {
                    if (isMatch(w + 1, i)) {
                        cache[w + 1][i] = 1;
                        return true;
                    }
                }
            }
            cache[w][s] = -1;
            return false;
        }

        private void printAnswer() {
            Collections.sort(matchPool);
            for (String s : matchPool) {
                System.out.println(s);
            }
        }
    }
}
