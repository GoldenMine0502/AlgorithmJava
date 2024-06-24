package kr.goldenmine.baekjoon.silver.silver1.p1495;

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
        int S = scan.nextInt();
        int M = scan.nextInt();

        int[] diffs = new int[N - 1];
        for(int i = 0; i < N - 1; i++) {
            diffs[i] = scan.nextInt();
        }
        int[] dp = new int[N];
        int[] dp_plus = new int[N];
        dp[1] = S;
        // M = 8 / S = 0 /
        // 4, 3, 2, 4, 3, 1
        // 4, 7, 9
        for(int i = 2; i < N; i++) {
            int offset = diffs[i - 2];
            int plus = dp[i - 1] + offset;

            dp[i] = plus;
            if(dp[i] > M) {
                // 초과한 경우
                // 가장 작은 값부터 빼본다.
                boolean minus = false;
                for(int j = i; j >= 1; j--) {
                    int next = dp[i] - dp[j] * 2;
                    if(dp_plus[j] == 1 && next <= M) {
                        dp[i] = next;
                        dp_plus[j] = 0;
                        minus = true;
                        break;
                    }
                }
                if(!minus) {
                    System.out.println(-1);
                    return;
                }
            } else {
                dp_plus[i] = 1; // i번째는 더해짐
            }
        }
        System.out.println(dp[N - 1]);

        // 14 40 243
        // 74 39 127 95 63 140 99 96 154 18 137 162 14 88
        // 150, 164, 2, 139,
    }
}