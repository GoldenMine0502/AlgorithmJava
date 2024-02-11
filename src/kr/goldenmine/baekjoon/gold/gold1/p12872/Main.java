package kr.goldenmine.baekjoon.gold.gold1.p12872;

import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int P;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        M = scan.nextInt();
        P = scan.nextInt();

        // 앞: 조건없음, 뒤: 조건있음
        // D(p,sel) = (N-sel+1) * D(p-1,sel-1) + (sel-M) * D(p-1,sel)(if, sel>M)
        long[][] arr = new long[P + 1][N + 1];
        arr[0][0] = 1;

        final int MOD = 1_000_000_007;

        for(int i = 1; i <= P; i++) {
            for(int j = 1; j <= N; j++) {
                arr[i][j] = ((N - j + 1) * arr[i - 1][j - 1]) % MOD;
                arr[i][j] %= MOD;
                if(j > M)
                    arr[i][j] += ((j - M) * arr[i - 1][j]) % MOD;
                arr[i][j] %= MOD;
            }
        }
        System.out.println(arr[P][N]);
    }
}
