package kr.goldenmine.baekjoon.bronze.bronze2.p10870;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        // f(n - 1) + f(n - 2) = f(n)

        System.out.println(fibonacci(N));
    }

    public static int fibonacci(int N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        if(N == 2) return 1;

        return fibonacci(N - 1) + fibonacci(N - 2);
    }
}
