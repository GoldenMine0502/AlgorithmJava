package kr.goldenmine.gold.gold4.p13250A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        double[] dp = new double[N + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = 1;

        // dp[N] =
//        int[] tables = {6, 5, 4, 3, 2, 1};

        for(int i = 2; i <= N; i++) {
            double total = 0;
            for(int j = 1; j <= 6; j++) {
                if(i - j >= 1) {
//                    System.out.println((i - j) + ", " + j + ": " + dp[i - j] + ", " + dp[j]);
                    total += dp[i - j]/* + dp[j]*/;
                }// else {
                //    total += 0;
                //}
//                System.out.println("total: " + total);
            }
//            System.out.println("i = " + i + ", total = " + total / 6.0);
            dp[i] = total / 6.0 + 1;
        }

        // i = 주사위의 개수
//        for(int i = 1; i <= N; i++) {
//            for(int j = 1; j <= 6; j++) {
//                if(i + j - 1 <= N) {
//                    dp[i + j - 1]++;
//                    System.out.println(i + j - 1);
//                }
//            }
//        }



        // 1 = 1, 2, 3, 4, 5, 6 -> 1
        // 2 = 1 + 1, 2, 3, 4, 5, 6 -> 1.17
        // 3 = 2 + 1, 1 + 2, 3, 4, 5, 6 = 1.17 + 1 + 1.17 + 1 + 1 + 1 + 1 + 1 = 1.42
        // 4 = 1 + 3, 2 + 2, 3 + 1, 4, 5, 6 = (1 + 1.39) + (1.17 + 1.17) + (1.39 + 1) + 1 + 1 + 1 = 1.52
        // 5 =

//        for(int i = 1; i <= N; i++) {
//            System.out.println(dp[i] / 6.0);
//        }

        System.out.println(dp[N]);
    }
}
