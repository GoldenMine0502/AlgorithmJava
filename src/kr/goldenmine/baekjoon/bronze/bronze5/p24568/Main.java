package kr.goldenmine.baekjoon.bronze.bronze5.p24568;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(Math.max(A * 8 + B * 3 - 28, 0));
    }
}
