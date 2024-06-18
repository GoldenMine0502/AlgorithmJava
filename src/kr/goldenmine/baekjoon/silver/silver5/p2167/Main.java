package kr.goldenmine.baekjoon.silver.silver5.p2167;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        /*
        1 2 3 4
        5 6 7 8
        9 1 2 3
         */
        int[][] ys = new int[N + 1][M + 1];
        int[][] xs = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                ys[i][j] = ys[i - 1][j] + arr[i][j];
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                xs[i][j] = xs[i][j - 1] + arr[i][j];
            }
        }

        int[][] total = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                total[i][j] = total[i - 1][j - 1] + ys[i - 1][j] + xs[i][j - 1] + arr[i][j];
            }
        }

        int T = scan.nextInt();

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int y1 = scan.nextInt();
            int x1 = scan.nextInt();
            int y2 = scan.nextInt();
            int x2 = scan.nextInt();

            sb.append(total[y2][x2] - total[y2][x1 - 1] - total[y1 - 1][x2] + total[y1 - 1][x1 - 1]);
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
