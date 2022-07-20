package kr.goldenmine.gold.gold4.p10830S;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        long B = scan.nextLong();
        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        int[][] result = pow(arr, arr, B);


        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((result[i][j] % 1000) + " ");
            }
            System.out.println();
        }
    }

    public static int[][] mul(int[][] arr, int[][] arr2) {
        int[][] result = new int[arr.length][arr.length];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                int sum = 0;

                for(int k = 0; k < arr.length; k++) {
                    sum += (arr[i][k] * arr2[k][j]) % 1000;
                }

                result[i][j] = sum;
            }
        }

        return result;
    }

    public static int[][] pow(int[][] original, int[][] arr, long exponent) {
        int[][] result = new int[arr.length][arr.length];

        if(exponent == 1) {
            // just copy
            for(int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    result[i][j] = arr[i][j];
                }
            }

            return result;
        }
        if(exponent == 2) {
            return mul(arr, arr);
        }

//        System.out.println(exponent);

        int[][] half = pow(original, arr, exponent / 2);

        if(exponent % 2 == 0) {
            return mul(half, half);
        } else {
            return mul(original, mul(half, half));
        }
    }
}
