package kr.goldenmine.baekjoon.silver.silver4.p1965;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] list = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = scan.nextInt();
        }

        int[] dp = new int[N + 1]; // 인덱스는 상자의 크기

        int max = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = 1; //1로 초기화
            for (int j = 1; j < i; j++) {
                if (list[j] < list[i] && dp[i] < dp[j] + 1) { //핵심 LIS 방식
                    dp[i] = dp[j] + 1;
                }
            }
            if (max < dp[i]) max = dp[i]; //그때그때마다 비교해서 max에 저장
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }
}
