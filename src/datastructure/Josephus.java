package datastructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Josephus {
    public void solution() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./input/datastructure/josephus");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        int caseCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < caseCount; i++) {
            String[] arr = br.readLine().split(" ");
            int n = Integer.parseInt(arr[0]);
            int k = Integer.parseInt(arr[1]);
            Node node = initNode(n);

            for (int j = 0, len = n - 2; j < len; j++) {
                node = node.delete(k);
            }
            String format = "%d %d";
            if (node.index < node.next.index) {
                System.out.println(String.format(format, node.index, node.next.index));
            } else {
                System.out.println(String.format(format, node.next.index, node.index));
            }
        }
    }

    private Node initNode(int n) {
        Node root = new Node(1);
        Node next = root;
        for (int i = 2; i <= n; i++) {
            Node newNode = new Node(i);
            next.add(newNode);
            next = newNode;
        }
        root.prev = next;
        next.next = root;
        return root;
    }

    static class Node {
        private final int index;
        private Node prev;
        private Node next;

        private Node(int index) {
            this.index = index;
        }

        private void add(Node n) {
            next = n;
            n.prev = this;
        }

        private Node delete(int k) {
            Node n = this;
            prev.next = next;
            next.prev = prev;
            for (int i = 0; i < k; i++) {
                n = n.next;
            }
            return n;
        }
    }
}
