package kr.goldenmine.platinum.platinum4.p5670S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static class TrieNode {
        TrieNode[] childs = new TrieNode[26];
        int childCount = 0;
        boolean isLeaf;
        boolean root;

        public TrieNode(boolean root) {
            this.root = true;
        }

        public TrieNode() {

        }

        public void add(String text) {
            add(text, 0);
        }

        public void add(String text, int pos) {
            int index = text.charAt(pos) - 'a';

            if (childs[index] == null) {
                childs[index] = new TrieNode();
                childCount++;
            }
            if (pos < text.length() - 1) { // last
                childs[index].add(text, pos + 1);
            } else {
                childs[index].isLeaf = true;
            }
        }

        public int calculateCount(String text, int pos, int count) {
            if (text.length() == pos) {
                return count;
            }

            int index = text.charAt(pos) - 'a';
            TrieNode child = childs[index];

            int finalChildCount = childCount + (isLeaf ? 1 : 0);

            if (finalChildCount == 1 && !root) { // 카운트 = 1이란 뜻은 자동완성 가능
                return child.calculateCount(text, pos + 1, count);
            } else {
//                System.out.println(text + ", " + pos + ", " + (count + 1));
                return child.calculateCount(text, pos + 1, count + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            try {
                int N = Integer.parseInt(line);
                List<String> texts = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    line = br.readLine();
                    texts.add(line);
                }

                TrieNode root = new TrieNode(true);
                for (int i = 0; i < N; i++) {
                    String text = texts.get(i);
                    root.add(text);
                }

                int total = 0;
                for (int i = 0; i < N; i++) {
                    String text = texts.get(i);
                    total += root.calculateCount(text, 0, 0);
                }

                System.out.printf("%.2f\n", (double) total / N);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
