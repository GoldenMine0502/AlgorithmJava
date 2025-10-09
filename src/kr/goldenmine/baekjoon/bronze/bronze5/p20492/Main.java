package kr.goldenmine.baekjoon.bronze.bronze5.p20492;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = (new Scanner(System.in)).nextInt();

        int a = N / 100 * 78;
        int b = N / 100 * 80 + N / 100 * 20 / 100 * 78;
        System.out.println(a + " " + b);
        System.out.println();
    }
}
