package kr.goldenmine.gold.gold3.p7579F;

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
        /* initialization */
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int K = scan.nextInt();

        int[] values = new int[N + 1];
        int[] weights = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            values[i] = scan.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            weights[i] = scan.nextInt();
        }

        int[][] dp = new int[N + 1][K + 1];
        Point[][] weightsUsed = new Point[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (weights[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                    weightsUsed[i][j] = new Point(i - 1, j);
                } else { // j >= weight

                    if (dp[i - 1][j] < dp[i - 1][j - weights[i]] + values[i]) {
                        dp[i][j] = dp[i - 1][j - weights[i]] + values[i];
                        weightsUsed[i][j] = new Point(i - 1, j - weights[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        weightsUsed[i][j] = new Point(i - 1, j);
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int[] lasts = new int[K];
        int index = 0;
        Point last = new Point(N, K);

        while (last != null) {
            Point lastlast = weightsUsed[last.x][last.y];

            if ((lastlast != null ? lastlast.y : 0) < last.y) {
                if (last.x > 0)
                    lasts[index++] = last.x;
            }
            last = lastlast;
        }

        int sum = 0;
        for(int i = 0; i < K; i++) {
            sum += values[lasts[i]];
        }

        System.out.println(sum);
    }
}
