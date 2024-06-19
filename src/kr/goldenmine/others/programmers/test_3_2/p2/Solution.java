package kr.goldenmine.others.programmers.test_3_2.p2;

import java.util.Arrays;

public class Solution {
    public int solution(int m, int n, int[][] puddles) {
        final int MOD = 1_000_000_007;

        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;

        for(int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == 1 && j == 1) continue;
                if(dp[i][j] == -1) continue;
//                int up = ((map[i - 1][j] == 0) ? dp[i - 1][j] : 0);
//                int left = ((map[i][j - 1] == 0) ? dp[i][j - 1] : 0);
                int up = dp[i - 1][j] != -1 ? dp[i - 1][j] : 0;
                int left = dp[i][j - 1] != -1 ? dp[i][j - 1] : 0;

                dp[i][j] = (up % MOD + left % MOD) % MOD;
//                System.out.println(dp[i - 1][j] + ", " + dp[i][j - 1] + ", " + dp[i][j]);
            }
        }

//        for(int i = 0; i <= n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        return (int) dp[n][m];
    }

    public static void main(String[] args) {
        int m = 10;
        int n = 4;

        int[][] puddles = {{2, 2}};

        System.out.println(new Solution().solution(m, n, puddles));
    }
}
