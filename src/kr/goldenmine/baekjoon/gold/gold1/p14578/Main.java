package kr.goldenmine.baekjoon.gold.gold1.p14578;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();

        final int MOD = 1_000_000_007;
        long[][] dp = new long[2][N + 1];
        dp[0][1] = (long) N * (N - 1) % MOD; // 처음 경우의 수
        dp[1][1] = 0;
        // 경우의 수 0 = 이전에 칠한 라인에 다른 색이 오는 경우
        // 1 = 이전에 칠하지 않은 라인에 다른 색이 오는 경우
        for(int i = 2; i <= N; i++) {
            dp[0][i] = (N - i) * dp[0][i - 1] % MOD;
            //dp[1][2] =;
        }
    }
}
