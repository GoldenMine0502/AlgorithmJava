package kr.goldenmine.baekjoon.bronze.bronze5.p28701;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        int N = (new Scanner(System.in)).nextInt();

        for(int i = 1; i <= N; i++) {
            sum1 += i;
            sum2 += i;
            sum3 += i * i * i;
        }
        System.out.println(sum1);
        System.out.println(sum2 * sum2);
        System.out.println(sum3);
    }
}
