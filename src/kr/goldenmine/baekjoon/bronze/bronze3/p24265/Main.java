package kr.goldenmine.baekjoon.bronze.bronze3.p24265;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long c = 0;
        for(int i = 1; i < n; i++) {
            int k = n - i + 1 - 1;
            c += k;
        }
        System.out.println(c);
        System.out.println(2);
    }
}
