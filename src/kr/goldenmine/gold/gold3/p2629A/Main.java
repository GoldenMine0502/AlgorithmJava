package kr.goldenmine.gold.gold3.p2629A;

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

    static int N;
    static int[] weights;

    // dp[y][x] : x번까지의 추를 사용했을 때 y 무게를 만들 수 있는지에 대한 여부
    public static void solve(boolean[][] dp, int C, int i, int w) {
        if(i > N || dp[w][i]) return;
//        System.out.println(w + ", " + i);
        dp[w][i] = true;
        solve(dp, C, i + 1, w + weights[i]); // 내 자리에 추 더함
        solve(dp, C, i + 1, Math.abs(w - weights[i])); // ?
        solve(dp, C, i + 1, w); // 그대로
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        N = scan.nextInt();
        weights = new int[31];
        for(int i = 0; i < N; i++) {
            weights[i] = scan.nextInt();
        }

        int T = scan.nextInt();
        for(int i = 0; i < T; i++) {
            int C = scan.nextInt();
            boolean[][] dp = new boolean[40001][31];
            solve(dp, C, 0, 0);

            System.out.print(dp[C][N] ? "Y " : "N ");
        }
    }
}
