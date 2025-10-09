package kr.goldenmine.baekjoon.bronze.bronze5.p31611;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        System.out.println(N % 7 == 2 ? 1 : 0);
    }
}
