package kr.goldenmine.baekjoon.gold.gold4.p5639S;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static class Tree {
        int value;
        Tree left;
        Tree right;

        @Override
        public String toString() {
            return "Tree{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    static int index = 0;

    public static void addValue(Tree root, int value) {
        if (root.value == 0) { // 초기화 안됨
            root.value = value;
        } else { // 초기화 되었으니 아래로 내려감
            if (value < root.value) { // 입력 값이 현재 값보다 작으면 왼쪽으로
                if (root.left == null) {
                    root.left = new Tree();
                    root.left.value = value;
                    return;
                }
                addValue(root.left, value);
            } else {
                if (root.right == null) {
                    root.right = new Tree();
                    root.right.value = value;
                    return;
                }
                addValue(root.right, value);
            }
        }
    }

    public static void makeTree(Tree root, int depth, int maxDepth, List<Integer> inputs) {
        root.value = inputs.get(index++);

        if (index < inputs.size() && depth < maxDepth) {
            Tree child1 = new Tree();
            root.left = child1;
            makeTree(child1, depth + 1, maxDepth, inputs);
        }

        if (index < inputs.size() && depth < maxDepth) {
            Tree child2 = new Tree();
            root.right = child2;
            makeTree(child2, depth + 1, maxDepth, inputs);
        }
    }

    static BufferedWriter writer;


    public static void prefix(Tree root) {
        System.out.println(root.value);

        if (root.left != null)
            prefix(root.left);
        if (root.right != null)
            prefix(root.right);

//        if (root.value != 0)
    }

    public static void postfix(Tree root) throws IOException {
        if (root.left != null)
            postfix(root.left);
        if (root.right != null)
            postfix(root.right);

        writer.write(String.valueOf(root.value));
        writer.newLine();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> inputs = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                inputs.add(Integer.parseInt(line));
            } catch (Exception ex) {
                break;
            }
        }

        Tree root = new Tree();
//        int depth = (int) Math.ceil(Math.log(inputs.size()));
//        makeTree(root, 0, depth, inputs);
//        System.out.println(root);
        for (int i = 0; i < inputs.size(); i++) {
            addValue(root, inputs.get(i));
        }

//        System.out.println("index: " + index);
//        prefix(root);
//        System.out.println();
        postfix(root);
        writer.flush();
    }
}
