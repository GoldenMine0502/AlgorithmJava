package kr.goldenmine.baekjoon.gold.gold3.p10986F;

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

    static class LongSumSegmentTree {
        private long[] arr;
        private long[] tree;
        private int mod;

        public LongSumSegmentTree(long[] arr, int mod) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];
            this.mod = mod;
            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start] % mod;

            int mid = (start + end) / 2;
            return (this.tree[node] = init(start, mid, node * 2) % mod + init(mid + 1, end, node * 2 + 1) % mod) % mod;
        }

        public long sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public long sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우
            int mid = (start + end) / 2;

            return (sum(start, mid, node * 2, left, right) % mod + sum(mid + 1, end, node * 2 + 1, left, right) % mod) % mod;
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 입력 부분
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] arr = new int[N];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }

        /*
        1 2 3 1 2
        1 0 0 1 0
        길이 1 -> 1
        길이 2 -> 2
        길이 3 -> 3
        길이 4 -> 0
        길이 5 -> 1

        N log N 정도로 풀어야함
        N * N * log N
         */

        // solve
        long[] cache = new long[M];
        long sum = 0;

        for(int i = 0; i < N; i++) {
            sum += arr[i];
            sum %= M;
            cache[(int)sum]++;
        }

//        System.out.println(Arrays.toString(cache));

        long answer = 0;
        answer += cache[0];

        for(int i = 0; i < M; i++) {
            answer += cache[i] * (cache[i] - 1) / 2;
        }

        System.out.println(answer);

//        for(int i = 0; i < cache.length; i++) {
//            System.out.println(cache[i]);
//        }
    }
}
