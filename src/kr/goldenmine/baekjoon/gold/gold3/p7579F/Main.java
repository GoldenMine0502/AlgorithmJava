package kr.goldenmine.baekjoon.gold.gold3.p7579F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    static class Point {
        int x;
        int y;
//        int count;
//        int max;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
//            this.count = count;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
//                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int C = scan.nextInt();

        int[] values = new int[N + 1];
        int[] weights = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            weights[i] = scan.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            values[i] = 100 - scan.nextInt();
        }

        int[] dp = new int[C + 1];

        final int MAX = 1_0000_0000;

        dp[0] = MAX;

        for (int i = 1; i <= C; i++) {
            dp[i] = dp[i - 1];
            for (int j = 1; j <= N; j++) {
                dp[i] = Math.max(dp[i], i >= weights[j] ? dp[i - weights[j]] + values[j] : MAX);
            }
        }

        System.out.println(Arrays.toString(dp));

        System.out.println(dp[C]);
    }
}
