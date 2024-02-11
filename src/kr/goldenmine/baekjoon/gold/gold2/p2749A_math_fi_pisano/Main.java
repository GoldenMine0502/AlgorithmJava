package kr.goldenmine.baekjoon.gold.gold2.p2749A_math_fi_pisano;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 9223372036854775807
        // 1000000000000000000
        // 1500000000000000000
        // 피보나치 수를 나눈 나머지는 항상 주기를 가진다. 이를 피사노 주기(Pisano Period)라 한다.
        int mod = 10000000;
        int K =   15000000;

        int[] arr = new int[K];

        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i < K; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % mod;
        }

        int N = (int) (scan.nextLong() % K);

        System.out.println(arr[N]);
    }

    public static long fibonacci(long N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        if(N == 2) return 1;

        return fibonacci(N - 1) + fibonacci(N - 2);
    }
}
