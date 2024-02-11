package kr.goldenmine.baekjoon.silver.silver5.p2740S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 값 입력 받기
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] m1 = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                m1[i][j] = scan.nextInt();
            }
        }

        scan.nextInt(); // 그냥 값을 주지를 말지
        int K = scan.nextInt();

        int[][] m2 = new int[M][K];

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < K; j++) {
                m2[i][j] = scan.nextInt();
            }
        }

        // solve
        int[][] m3 = new int[N][K];

        for(int i = 0; i < N; i++) { // N쪽은 가로로
            for(int j = 0; j < K; j++) { // K쪽은 세로로
                int sum = 0;
                for(int m = 0; m < M; m++) {
                    sum += m1[i][m] * m2[m][j];
                }

                m3[i][j] = sum;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++) {
                System.out.print(m3[i][j] + " ");
            }
            System.out.println();
        }

    }
}
