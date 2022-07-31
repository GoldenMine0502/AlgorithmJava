package kr.goldenmine.gold.gold2.p11444A;

import java.util.Scanner;

public class Main {
    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 9,223,372,036,854,775,807
        // 1,000,000,000,000,000,000
        long N = scan.nextLong();

        if(N == 0) {
            System.out.println(0);
            return;
        }

        if(N == 1) {
            System.out.println(1);
            return;
        }

        if(N == 2) {
            System.out.println(1);
            return;
        }

        int[][] A = new int[2][2];
        A[0][0] = 1;
        A[1][0] = 1;
        A[0][1] = 1;
        A[1][1] = 0;

        int[][] result = pow(A, A, N - 2);

        int[][] B = new int[2][1];
        B[0][0] = 1;
        B[1][0] = 1;

        // 2 by 1
        int[][] answer = mul(2, 2, 1, result, B);

        System.out.println(answer[0][0]);
    }

    public static int[][] mul(int[][] arr, int[][] arr2) {
        return mul(arr.length, arr.length, arr.length, arr, arr2);
    }

    public static int[][] mul(int N, int M, int K, int[][] arr, int[][] arr2) {
        int[][] result = new int[N][K];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++) {
                long sum = 0;

                for(int k = 0; k < M; k++) {
                    sum += ((long)arr[i][k] % mod * arr2[k][j] % mod) % mod;
                }

                result[i][j] = (int) (sum % mod);
            }
        }

        return result;
    }

    public static int[][] pow(int[][] original, int[][] arr, long exponent) {
        if(exponent == 1) {
            return original;
        }

        int[][] half = pow(original, arr, exponent / 2);

        if(exponent % 2 == 0) {
            return mul(half, half);
        } else {
            return mul(original, mul(half, half));
        }
    }
}
