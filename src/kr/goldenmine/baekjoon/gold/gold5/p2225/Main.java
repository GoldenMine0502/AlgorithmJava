package kr.goldenmine.baekjoon.gold.gold5.p2225;

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
        int K = scan.nextInt();

        // dp[a][b] a숫자까지 b개로 갈 수 있는 경우의 수
        int[][] dp = new int[K + 1][N + 1];

        // 한개로는 하나로만 갈 수 있다.

        // 합분해 -> dp[n][k] += dp[n-n'][k-1]
        // k = 2일때 n=1 -> dp[1][2] = dp[1][1]
        // k = 2일때 n=2 -> dp[2][2] = dp[1][1] + dp[2][1]
        // k = 3일때 n=2 -> dp[3][2] = dp[1][1] + dp[2][1] + dp[3][1]
        // k = 3일때 n=2 -> dp[3][3] = dp[3][2] + dp[3][1] +

//        for (int n = 0; n <= N; n++) {
//            for (int k = 1; k <= K; k++) {
//                for (int n2 = n - 1; n2 >= 0; n2--) {
//                    dp[n][k] += dp[n - n2][k - 1]; // n2를 더했다고 생각하기
//                }
////                dp[n][k] += dp[n][k - 1];
//            }
//        }

        for (int i = 1; i <= K; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000; // 1000000000으로 나누는 걸 출력할 때 주면 틀렸다고 뜸.
            }
        }

//        for (int k = 1; k <= N; k++) {
//            System.out.print(k + " ");
//        }
//        System.out.println();

//        int sum = 0;
//        for (int k = 1; k <= K; k++) {
//            for (int n = 1; n <= N; n++) {
//                System.out.print(dp[n][k] + " ");
//                sum += dp[n][k];
//            }
//            System.out.println();
//        }

//        System.out.println("sum: " + sum);

        System.out.println(dp[K][N]);
    }
}
