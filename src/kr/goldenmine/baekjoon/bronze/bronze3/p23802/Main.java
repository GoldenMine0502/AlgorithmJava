package kr.goldenmine.baekjoon.bronze.bronze3.p23802;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N * 5; j++) {
                System.out.print("@");
            }
            System.out.println();
        }

        for(int i = 0; i < N * 4; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print("@");
            }
            System.out.println();
        }
    }
}
