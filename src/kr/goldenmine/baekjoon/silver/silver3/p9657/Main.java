package kr.goldenmine.baekjoon.silver.silver3.p9657;

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
    static int[] dp = new int[1001];
    static int c = 0;

    public static void dfs(int n, int count) {
        if(n > N) return;


        if(dp[n] < count) {
            return;
        }

        c++;
        dp[n] = Math.min(dp[n], count);

        if(n - 4 <= N)
            dfs(n + 4, count + 1);

        if(n - 3 <= N)
            dfs(n + 3, count + 1);

        if(n - 1 <= N)
            dfs(n + 1, count + 1);
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        N = scan.nextInt();

        for(int i = 0; i <= 1000; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

//        dp[1] = 1;
//        dp[2] = 1;
//        dp[3] = 1;

        dfs(0, 0);
//        dp[2] = 1;

        System.out.println(dp[N] % 2 == 1 ? "SK" : "CY");
//        System.out.println(dp[N]);
//
//        for(int i = 1; i <= N; i++) {
//            System.out.println(dp[i]);
//        }
//
//        System.out.println(c++);
    }
}

