package kr.goldenmine.baekjoon.gold.gold1.p11505A;


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

    static class LongMulSegmentTree {
        private long[] arr;
        private long[] tree;
        private int mod;

        public LongMulSegmentTree(long[] arr, int mod) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];
            this.mod = mod;

//            Arrays.fill(tree, BigInteger.ZERO);

            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;

            return this.tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % mod;
        }

        public long mul(int left, int right) {
            return mul(0, arr.length - 1, 1, left, right);
        }

        public long mul(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 1L;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;
            return (mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right)) % mod;
        }

        public void update(int index, long value) {
            update(0, arr.length - 1, 1, index, value);
        }

        public long update(int start, int end, int node, int index, long value) {
            if(index < start || index > end)
                return tree[node];

            // 0일때는 어차피 곱했을때 싹다 0이였을테니 0에서 n으로 바뀌면 영향받는 모든 구간은 6이다.
            // by zero 에러 해결
//            if (arr[index] != 0)
//                tree[node] = tree[node] / arr[index] * (arr[index] + diff);
//            else
//                tree[node] = diff;

            if(node == 1) {
                arr[index] = value;
            }

            if (start == end) {
                return tree[node] = value;
            }

            int mid = (start + end) / 2;

            return tree[node] = (update(start, mid, node * 2, index, value) * update(mid + 1, end, node * 2 + 1, index, value)) % mod;
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int K = scan.nextInt();
        int mod = 1000000007;

        long[] arr = new long[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextLong();
        }

        LongMulSegmentTree tree = new LongMulSegmentTree(arr, mod);

//        tree.printTreeAll();

        for(int i = 0; i < M + K; i++) {
            int a = scan.nextInt();
            long b = scan.nextLong();
            long c = scan.nextLong();

            if(a == 1) {
                tree.update((int)b - 1, c);
            } else /*if(command == 2)*/ {
                long sum = tree.mul((int)b - 1, (int)c - 1);

                System.out.println(sum);
            }
        }
    }
}
