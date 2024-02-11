package kr.goldenmine.baekjoon.silver.silver1.p15988S;

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

        int T = scan.nextInt();
        final int MOD = 1_000_000_009;

        StringBuilder sb = new StringBuilder();

//        long prev1 = 1;
//        long prev2 = 2;
//        long prev3 = 4;

        long[] dp = new long[1000001];

        dp[1] = 1; // = 1
        dp[2] = 2; // = 1 + 1 = 2
        dp[3] = 4; // = 1 + 1 + 1 = 1 + 2 = 2 + 1 = 3

        for(int j = 4; j <= 1000000; j++) {
            dp[j] += dp[j - 1];
            dp[j] += dp[j - 2];
            dp[j] += dp[j - 3];
            dp[j] %= MOD;
        }

        for(int i = 0; i < T; i++) {
            int N = scan.nextInt();

            sb.append(dp[N]);
            sb.append('\n');
//            int N = scan.nextInt();
//
//            if(N == 1) { sb.append("1\n"); continue; }
//            if(N == 2) { sb.append("2\n"); continue; }
//            if(N == 3) { sb.append("4\n"); continue; }
//
//            long res;
//            for(int j = 4; j <= N; j++) {
//                res = 0;
//                res += prev1;
//                res += prev2;
//                res += prev3;
//                res %= MOD;
//
//                prev1 = prev2;
//                prev2 = prev3;
//                prev3 = res;
//            }
//            sb.append(prev3);
//            sb.append('\n');
        }
        System.out.println(sb);
    }
}
