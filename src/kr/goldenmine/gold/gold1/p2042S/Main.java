package kr.goldenmine.gold.gold1.p2042S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
//import java.util.Arrays;
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

    static class BigIntegerSumSegmentTree {
        private BigInteger[] arr;
        private BigInteger[] tree;

        public BigIntegerSumSegmentTree(BigInteger[] arr) {
            this.arr = arr;
            this.tree = new BigInteger[4 * arr.length];

//            Arrays.fill(tree, BigInteger.ZERO);

            init(0, arr.length - 1, 1);
        }

        public BigInteger init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2).add(init(mid + 1, end, node * 2 + 1));
        }

        public BigInteger sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public BigInteger sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return BigInteger.ZERO;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;

            return sum(start, mid, node * 2, left, right).add(sum(mid + 1, end, node * 2 + 1, left, right));
        }

        public void updateSet(int index, BigInteger value) {
            updateSet(0, arr.length - 1, 1, index, value);
        }

        public void updateSet(int start, int end, int node, int index, BigInteger value) {
            BigInteger diff = value.subtract(arr[index]);
//            arr[index] = value;

            update(start, end, node, index, diff);
        }

        public void update(int index, BigInteger diff) {
            update(0, arr.length - 1, 1, index, diff);
        }

        public void update(int start, int end, int node, int index, BigInteger diff) {
            if(index < start || index > end) return;

            if(node == 1) {
                arr[index] = arr[index].add(diff);
            }

            tree[node] = tree[node].add(diff);

            if (start == end) return;

            int mid = (start + end) / 2;

            update(start, mid, node * 2, index, diff);
            update(mid + 1, end, node * 2 + 1, index, diff);
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();

        BigInteger[] arr = new BigInteger[N];

        for(int i = 0; i < N; i++) {
            arr[i] = new BigInteger(scan.next());
        }

        BigIntegerSumSegmentTree tree = new BigIntegerSumSegmentTree(arr);

//        tree.printTreeAll();

        for(int i = 0; i < M + K; i++) {
            int a = scan.nextInt();
            String b = scan.next();
            String c = scan.next();

            if(a == 1) {
                tree.updateSet(Integer.parseInt(b) - 1, new BigInteger(c));
            } else /*if(command == 2)*/ {
                BigInteger sum = tree.sum(Integer.parseInt(b) - 1, Integer.parseInt(c) - 1);

                System.out.println(sum);
            }
        }
    }
}
