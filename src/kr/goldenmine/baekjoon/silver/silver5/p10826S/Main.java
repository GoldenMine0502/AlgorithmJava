package kr.goldenmine.baekjoon.silver.silver5.p10826S;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 9223372036854775807
        // 1000000000000000000
        // 1500000000000000000
        // 피보나치 수를 나눈 나머지는 항상 주기를 가진다. 이를 피사노 주기(Pisano Period)라 한다.
//        int mod = 10000000;
        int K =   10001;

        BigInteger[] arr = new BigInteger[K];

        arr[0] = BigInteger.ZERO;
        arr[1] = BigInteger.ONE;

        for(int i = 2; i < K; i++) {
            arr[i] = arr[i - 1].add(arr[i - 2]);
        }

        int N = scan.nextInt();

        System.out.println(arr[N]);
    }

    public static long fibonacci(long N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        if(N == 2) return 1;

        return fibonacci(N - 1) + fibonacci(N - 2);
    }
}

