package kr.goldenmine.baekjoon.bronze.bronze5.p27219;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = (new Scanner(System.in)).nextInt();

        int n = N / 5;
        int nm = N % 5;

        for(int i = 0; i < n; i++) {
            System.out.print('V');
        }
        for(int i = 0; i < nm; i++) {
            System.out.print('I');
        }
    }
}
