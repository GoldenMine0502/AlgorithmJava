package kr.goldenmine.baekjoon.silver.silver4.p1049;

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

        // 끊어진 기타줄의 개수 N과 기타줄 브랜드 M개
        int N = scan.nextInt();
        int M = scan.nextInt();

        int min6 = 999999;
        int min1 = 999999;
        for(int i = 0; i < M; i++) {
            int six = scan.nextInt();
            int one = scan.nextInt();

            if(min6 > six) {
                min6 = six;
            }

            if(min1 > one) {
                min1 = one;
            }
        }

        int[] dp = new int[N + 1 + 6];
        dp[0] = 0;

        for(int i = 1; i < dp.length; i++) {
            dp[i] = 999999999;
        }

        for(int i = 1; i < dp.length; i++) {
            if (i >= 6) { // 기타줄 6개 살 때 고려함
                dp[i] = Math.min(dp[i], dp[i - 6] + min6);
            }
            dp[i] = Math.min(dp[i], dp[i - 1] + min1);
            // 기타줄 한 개 살 때 고려함
        }

        int min = dp[N];
        for(int i = N; i < N + 6; i++) {
            min = Math.min(min, dp[i]);
        }
        System.out.println(min);
    }
}
