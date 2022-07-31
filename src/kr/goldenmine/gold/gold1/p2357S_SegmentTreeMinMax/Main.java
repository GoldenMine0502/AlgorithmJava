package kr.goldenmine.gold.gold1.p2357S_SegmentTreeMinMax;

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

    static class LongMaxSegmentTree {
        private long[] arr;
        private long[] tree;

        public LongMaxSegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];

            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;

            return this.tree[node] = Math.max(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
        }

        public long find(int left, int right) {
            return find(0, arr.length - 1, 1, left, right);
        }

        public long find(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return Integer.MIN_VALUE;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;
            return Math.max(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
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
    }

    static class LongMinSegmentTree {
        private long[] arr;
        private long[] tree;

        public LongMinSegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];

            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;

            return this.tree[node] = Math.min(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
        }

        public long find(int left, int right) {
            return find(0, arr.length - 1, 1, left, right);
        }

        public long find(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return Integer.MAX_VALUE;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;
            return Math.min(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
        }

        public void update(int start, int end, int node, int index, int value) {

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
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        long[] arr = new long[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextLong();
        }

        LongMaxSegmentTree maxTree = new LongMaxSegmentTree(arr);
        LongMinSegmentTree minTree = new LongMinSegmentTree(arr);

//        maxTree.printTreeAll();
//        minTree.printTreeAll();

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 0; i < M; i++) {
            int left = scan.nextInt();
            int right = scan.nextInt();

            long max = maxTree.find(left - 1, right - 1);
            long min = minTree.find(left - 1, right - 1);

            output.write(String.valueOf(min));
            output.write(" ");
            output.write(String.valueOf(max));
            output.newLine();
        }

        output.flush();
    }
}
