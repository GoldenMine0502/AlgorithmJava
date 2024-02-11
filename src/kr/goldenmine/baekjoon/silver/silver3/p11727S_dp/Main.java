package kr.goldenmine.baekjoon.silver.silver3.p11727S_dp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        i - 2에서 (가로로 두칸 꽉 채우거나 , 2*2 채우는 경우),
        세로로 한칸 채우는 경우.
        dp[3] = dp[1] * 2 + dp[2]

         */

        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int[] cache = new int[1001];
        int mod = 10007;

        cache[1] = 1;
        cache[2] = 3;
        for(int i = 3; i <= N; i++) {
            cache[i] = ((cache[i - 2] * 2) % mod  + cache[i - 1]) % mod;
        }

        System.out.println(cache[N] % mod);
    }
}
