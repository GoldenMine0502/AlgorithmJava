package kr.goldenmine.baekjoon.gold.gold1.p2718A;

import java.util.Scanner;

public class Main {

    public static int solve(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        if(N >= 2)
            dp[2] = 5;
//        if(N >= 3)
//            dp[3] = 11;

        for(int i = 3; i <= N; i++) {
            int t1 = dp[i - 1];
            int t2 = dp[i - 2] * 4;
            int total = 0;
            for(int j = i - 3; j >= 0; j--) {
                if((i - j) % 2 == 1) { // 홀수인 경우
                    total += dp[j] * 2;
//                    System.out.println("1: " + j + ", " + (j) + ", " + dp[j] * 2);
                } else {
                    total += dp[j] * 3;
//                    System.out.println("2: " + j + ", " + (j) + ", " + dp[j] * 3);
                }
            }

//            System.out.println(t1 + ", " + t2 + ", " + total);

            dp[i] = t1 + t2 + total;
        }

        return dp[N];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        for(int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            System.out.println(solve(N));
        }
//        System.out.println(solve(2));
//        System.out.println(solve(3));
//        System.out.println(solve(7));
//        System.out.println(solve(7));
    }
}
