package datastructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Brackets2 {
    private final static char[] BRACKETS = {'(', ')', '{', '}', '[', ']'};

    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/datastructure/brackets2");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String input = br.readLine();
            if (isRightBracket(input)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private boolean isRightBracket(String str) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            int id = findBracketId(str.charAt(i));
            if (id % 2 == 0) {
                stack.push(id);
            } else {
                if (stack.isEmpty() || stack.pop() != (id - 1)) {
                    return false;
                }
            }
        }
        if(stack.isEmpty()) {
            return true;
        }
        return false;
    }

    private int findBracketId(char c) {
        for (int i = 0; i < BRACKETS.length; i++) {
            if (c == BRACKETS[i]) {
                return i;
            }
        }
        return -1;
    }
}
