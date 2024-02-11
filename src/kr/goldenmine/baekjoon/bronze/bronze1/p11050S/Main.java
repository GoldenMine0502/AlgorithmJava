package kr.goldenmine.baekjoon.bronze.bronze1.p11050S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();

        long result = 1;

        // n!
        for(int i = 2; i <= N; i++) {
            result *= i;
        }

        // r!
        for(int i = 2; i <= K; i++) {
            result /= i;
        }

        // (n - r)! ->
        for(int i = 2; i <= (N - K); i++) {
            result /= i;
        }

        System.out.println(result);
    }
}
