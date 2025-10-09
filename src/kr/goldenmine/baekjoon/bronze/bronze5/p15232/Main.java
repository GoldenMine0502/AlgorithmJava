package kr.goldenmine.baekjoon.bronze.bronze5.p15232;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int R = scan.nextInt();
        int C = scan.nextInt();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                System.out.print("*");
            }
            System.out.println();

        }
    }
}
