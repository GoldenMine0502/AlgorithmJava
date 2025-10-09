package kr.goldenmine.baekjoon.bronze.bronze5.p5341;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true) {
            int N = scan.nextInt();
            if(N == 0) break;

            System.out.println(N * (N + 1) / 2);
        }
    }
}
