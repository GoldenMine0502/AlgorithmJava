package kr.goldenmine.baekjoon.gold.gold1.p1562;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();

        // dp[i][bit][j] = dp[i + 1][bit][j]
        int[][][] dp = new int[10][2][N + 1];

        // 0 제외
        for(int j = 1; j < 10; j++) {
//            dp[j][1] = 1;
        }

        for(int i = 2; i <= N; i++) {
            // add nearby
            for(int j = 0; j < 10; j++) {
//                dp[j][i] += j < 9 ? dp[j + 1][i - 1] : 0;
//                dp[j][i] += j > 0 ? dp[j - 1][i - 1] : 0;
            }
        }

        int sum = 0;
        for(int j = 0; j < 10; j++) {
//            sum += dp[j][N];
        }
        System.out.println(sum);
    }
}
