package kr.goldenmine.baekjoon.bronze.bronze5.p27324;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = (new Scanner(System.in)).nextInt();

        int n10 = N / 10;
        int n1 = N % 10;

        if(n10 == n1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
