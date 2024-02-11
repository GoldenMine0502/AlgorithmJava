package kr.goldenmine.baekjoon.gold.gold1.p10868S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static class MinMaxSegmentTree {
        class Node {
            int min;
            int max;

            public Node(int min, int max) {
                this.min = min;
                this.max = max;
            }

            @Override
            public String toString() {
                return "(" + min + ", " + max + ")";
            }
        }

        private int[] arr;
        private Node[] tree;

        public MinMaxSegmentTree(int[] arr) {
            this.arr = arr;
            this.tree = new Node[4 * arr.length];

            for(int i = 0; i < this.tree.length; i++) {
                this.tree[i] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            init(0, arr.length - 1, 1);
        }

        public Node merge(Node a, Node b) {
            return new Node(Math.min(a.min, b.min), Math.max(a.max, b.max));
        }

        public Node init(int start, int end, int node) {
            if(start == end) return this.tree[node] = new Node(arr[start], arr[start]);

            int mid = (start + end) / 2;

            Node left = init(start, mid, node * 2);
            Node right = init(mid + 1, end, node * 2 + 1);

            return this.tree[node] = merge(left, right);
        }

        public Node find(int left, int right) {
            return find(0, arr.length - 1, 1, left, right);
        }

        public Node find(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;
            return merge(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
        }

        public void update(int index, int value) {
            update(0, arr.length - 1, 1, index, value);
        }

        public Node update(int start, int end, int node, int index, int value) {
//            System.out.println("access " + start + ", " + end + ", " + node + ", " + index);
            if(index < start || index > end) return tree[node];

            if(node == 1) {
                arr[index] = value;
            }

            if (start == end) { // 최하위 클래스
//                System.out.println("update node " + node + " to (" + value + ", " + value + "), " + start + " " + index);
                return tree[node] = new Node(value, value);
            }

            int mid = (start + end) / 2;

            Node a = update(start, mid, node * 2, index, value);
            Node b = update(mid + 1, end, node * 2 + 1, index, value);

//            System.out.println("update node " + node + " from" + tree[node] + " to " + merge(a, b) + ": " + a + " (" + start + ", " + mid + ") / " + b + " (" + (mid + 1) + ", " + end + ")" + " " + index);
            return tree[node] = merge(a, b);
        }

        public void printArrAll() {
            for(int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }

        public void printAll() {
//            for(int i = 0; i < tree.length - 1; i++) {
//                System.out.print(i + " ");
//            }
            System.out.println();
            printArrAll();
            printTreeAll();
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        MinMaxSegmentTree tree = new MinMaxSegmentTree(arr);

        for(int i = 0; i < M; i++) {
            int left = scan.nextInt();
            int right = scan.nextInt();

            int min = tree.find(left - 1, right - 1).min;

            System.out.println(min);
        }
    }
}
