package kr.goldenmine.baekjoon.gold.gold1.p11401S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();
        int mod = 1000000007;

        // (n+1)C(r+1) = nCr + nC(r+1)

//        System.out.println(factorial(5, mod));

        // 그냥 페르마의소정리3.png에 있는거 그대로 갖다 쓰면 풀린다...
        // https://st-lab.tistory.com/241
        long first = factorial(N, mod) % mod;
        long second = pow((factorial(N - K, mod) % mod) * (factorial(K, mod) % mod), mod - 2, mod) % mod;

        System.out.println((first * second) % mod);
    }

    public static long factorial(int n, int mod) {
        long result = 1;

        for(int i = 2; i <= n; i++) {
            result = ((result % mod) * (i % mod)) % mod;
        }

        return result;
    }


    // A^exponent
    public static long pow(long A, long exponent, int mod) {
        if(exponent == 1) return A % mod;

        long halfpow = pow(A, exponent / 2, mod);

        if(exponent % 2 == 0)
            return halfpow * halfpow % mod;
        else
            return ((halfpow * halfpow % mod) * (A % mod) % mod);
    }

//    public static int BC(int N, int K) {
//        if(N == K || K == 0) {
//            return 1;
//        }
//
//        return BC(N - 1, K - 1) + BC(N - 1, K);
//    }
}
