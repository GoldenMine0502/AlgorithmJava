package kr.goldenmine.gold.gold5.p15989S;

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

    public static int solve(int N) {
        int[][] dp = new int[3][Math.max(5, N + 1)];

        // 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 4가지가 있다.
        // 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
        // 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다.

        // sort가 된 상태로 생각
        // 3 = 2 + 1 = 1 + 1 + 1
        // 나보다 큰 값을 안더하면 됨

        // dp[0] -> 마지막 값이 1로 끝난 개수 -> dp[0][n] = dp[2][n - 1] + dp[1][n - 1] + dp[0][n - 1]
        // dp[1] -> 마지막 값이 2로 끝난 개수 -> dp[1][n] = dp[2][n - 2] + dp[1][n - 2]
        // dp[2] -> 마지막 값이 3으로 끝난 개수 -> dp[2][n] = dp[2][n - 3]

        dp[0][0] = 0;
        dp[1][0] = 0;
        dp[2][0] = 0;

        dp[0][1] = 1;
        dp[1][1] = 0;
        dp[2][1] = 0;

        // 2 = 1 + 1 = 2
        dp[0][2] = 1;
        dp[1][2] = 1;
        dp[2][2] = 0;

        // 3 = 3 = 2 + 1 = 1 + 1 + 1
        dp[0][3] = 2;
        dp[1][3] = 0;
        dp[2][3] = 1;

        // 4 = 3 + 1 = 2 + 2 = 2 + 1 + 1 = 1 + 1 + 1 + 1
        dp[0][4] = 3;
        dp[1][4] = 1;
        dp[2][4] = 0;

        for(int i = 5; i <= N; i++) {
            dp[0][i] = dp[2][i - 1] + dp[1][i - 1] + dp[0][i - 1];
            dp[1][i] = dp[2][i - 2] + dp[1][i - 2];
            dp[2][i] = dp[2][i - 3];
        }

        return dp[0][N] + dp[1][N] + dp[2][N];
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();
        while(T-- > 0) {
            int N = scan.nextInt();
            int result = solve(N);

            System.out.println(result);
        }

    }
}
