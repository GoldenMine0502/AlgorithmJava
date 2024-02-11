package kr.goldenmine.baekjoon.gold.gold5.p2293S_dp_coin;

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

        int n = scan.nextInt();
        int k = scan.nextInt();

        int[] arr = new int[k];

        for(int i = 0 ; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        // dp[n][k] n = 현재 가치에 대한 경우의 수, 1부터 k까지 동전을 활용한 경우의 수
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                if(j >= arr[i]) {
                    dp[j] += dp[j - arr[i]];
                }
            }
        }

        System.out.println(dp[k]);
    }
}
