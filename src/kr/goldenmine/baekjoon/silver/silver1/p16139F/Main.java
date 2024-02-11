package kr.goldenmine.baekjoon.silver.silver1.p16139F;

import java.io.*;
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

    static class SegmentTree {
        private char[] arr;
        private int[] tree;
        private char ch;

        public SegmentTree(char[] arr, char ch) {
            this.arr = arr;
            this.tree = new int[4 * arr.length];
            this.ch = ch;
            init(0, arr.length - 1, 1);
        }

        public int init(int start, int end, int node) {
            if(start == end) return this.tree[node] = (arr[start] == ch ? 1 : 0);

            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public int sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public int sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;

            return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // 이문제 세그먼트 트리로도 못풀고 O(log N) 문제 하나당 O(1) 수준으로 풀어야함.
        FastReader scan = new FastReader();

        String text = scan.next();
        char[] arr = text.toCharArray();

        int N = scan.nextInt();

        SegmentTree[] trees = new SegmentTree[26];

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < N; i++) {
            char ch = scan.next().charAt(0);
            int left = scan.nextInt();
            int right = scan.nextInt();

            if(trees[ch - 'a'] == null) {
                trees[ch - 'a'] = new SegmentTree(arr, ch);
            }

            int sum = trees[ch - 'a'].sum(left, right);


            output.write(String.valueOf(sum));
            output.newLine();
        }

        output.flush();
    }
}
