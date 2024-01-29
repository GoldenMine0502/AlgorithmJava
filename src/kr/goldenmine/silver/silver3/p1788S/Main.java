package kr.goldenmine.silver.silver3.p1788S;

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

        final int BAEKMAN = 100 * 10000;
        final int MOD = 10 * 10000 * 10000;
        int[] dp = new int[BAEKMAN * 2 + 1]; // -100만~100만

        dp[BAEKMAN] = 0;
        dp[BAEKMAN + 1] = 1;

        for(int i = BAEKMAN + 2; i <= BAEKMAN * 2; i++) {
            dp[i] = (dp[i - 1] % MOD + dp[i - 2] % MOD) % MOD;
        }

        for(int i = BAEKMAN + 1; i >= 2; i--) {
            // f(n) = f(n - 1) + f(n - 2)
            // f(n - 2) = f(n) - f(n - 1)
            dp[i - 2] = (dp[i] % MOD - dp[i - 1] % MOD) % MOD;
        }

        int res = dp[N + BAEKMAN];
        if(res > 0) {
            System.out.println(1);
        } else if(res == 0) {
            System.out.println(0);
        } else {
            System.out.println(-1);
        }

        System.out.println(Math.abs(res));
    }
}
