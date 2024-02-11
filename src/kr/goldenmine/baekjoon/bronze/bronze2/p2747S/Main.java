package kr.goldenmine.baekjoon.bronze.bronze2.p2747S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int a = 0;
        int b = 1;

        if(N == 0) {
            System.out.println(a);
        }

        if(N == 1) {
            System.out.println(b);
        }

        for(int i = 2; i <= N; i++) {
            int c = a + b;

            if(i == N) {
                System.out.println(c);
            } else {
                a = b;
                b = c;
            }
        }
    }
}
