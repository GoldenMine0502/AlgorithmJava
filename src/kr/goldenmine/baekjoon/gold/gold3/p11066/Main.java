package kr.goldenmine.baekjoon.gold.gold3.p11066;

import java.io.*;
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

    static int testcase(int N, int[] list) {
        int[] sum = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + list[i];
        }

//        System.out.println(Arrays.toString(sum));

        int[][] dp = new int[N + 1][N + 1];

        // dp[s][f] = s부터 f까지 합쳤을때 최소 수
        for (int i = 1; i <= N; i++) {
//            dp[i][i] = list[i];
        }

        // s = 1, f = 4
        // dp[1][4] = min(dp[1][3] + dp[4][4], dp[1][2] + dp[3][4], dp[1][1] + dp[2][4])

        // len = 2, s = 1, f = 2 cancel
        // len = 2, s = 1, f = 3 do
        // len = 2, s = 2, f = 3 cancel
        // len = 2, s = 2, f = 4 do
//        for(int size = 1; size <= N; size++) {
//            for(int i = 0; i < N - size; i++) {
//                for(int sep = i; sep < i + size; sep++) {
//                    dp[i][i + size] = Math.min(dp[i][i + size], dp[i][sep] + dp[sep][i + size]);
//                }
//            }
//        }
        for(int len = 1; len <= N; len++) {
            for(int s = 1; s <= N - len; s++) {
                int f = s + len;
                dp[s][f] = Integer.MAX_VALUE;
                for(int mid = s; mid < f; mid++) {
                    dp[s][f] = Math.min(dp[s][f], dp[s][mid] + dp[mid + 1][f] + sum[f] - sum[s - 1]);
                }
            }
        }
//        for (int len = 1; len < N; len++) {
//            for (int s = 1; s <= N - len; s++) {
//                int f = s + len;
//
//                dp[s][f] = Math.min(dp[s][f], dp[s][s] + dp[s + 1][f]);
//                dp[s][f] = Math.min(dp[s][f], dp[s][f - 1] + dp[f][f]);
//            }
//        }
        return dp[1][N];
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = scan.nextInt();

        while (T-- > 0) {
            int N = scan.nextInt();
            int[] list = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                list[i] = scan.nextInt();
            }
            writer.write(String.valueOf(testcase(N, list)));
            writer.newLine();
        }
        writer.flush();
    }
}
