package kr.goldenmine.baekjoon.bronze.bronze5.p27433;

import java.util.Scanner;

public class Main {
    static long factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();

        System.out.println(factorial(N));
    }
}
