package kr.goldenmine.baekjoon.silver.silver3.p17212S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();

        int[] coins = {1, 2, 5, 7};
        int[] dp = new int[N + 1];

        dp[0] = 0;

        for(int i = 1; i <= N; i++) {
            dp[i] = i; // 1원짜리 n개 내는게 최대임
            for(int coin : coins) {
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
