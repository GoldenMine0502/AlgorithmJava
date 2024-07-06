package kr.goldenmine.baekjoon.silver.silver3.p17175S;

import java.util.Scanner;

public class Main {
    static int count = 0;
    static int fibonacci(int n) { // 호출
        count++;
        if (n < 2) {
            return n;
        }
        return fibonacci(n-2) + fibonacci(n-1);
    }

    // 1 3 5 9 15 25 41 67 109
    // 2 2 4 6 10 16 26 42 // 차분이 피보나치임
    // 0 2 2 4 6 8 12

    public static void main(String[] args) throws Exception {
        final long MOD = 1_000_000_007L;
        int N = new Scanner(System.in).nextInt();

        long[] arr = new long[101];
        long[] diff = new long[100];

        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 3;
        arr[3] = 5;
        arr[4] = 9;

        diff[0] = 0;
        diff[1] = 2;
        diff[2] = 2;
        diff[3] = 4;
        diff[4] = 6;

        for(int i = 5; i < N; i++) {
            diff[i] = (diff[i - 1] + diff[i - 2]) % MOD;
        }

        for(int i = 5; i <= N; i++) {
            arr[i] = (arr[i - 1] + diff[i - 1] + MOD) % MOD;
        }

//        fibonacci(N);
        System.out.println(arr[N]);
//        System.out.println(count);
    }
}
