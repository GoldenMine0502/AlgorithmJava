package kr.goldenmine.baekjoon.bronze.bronze5.p32929;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt() - 1;

        String str = "UOS";

        System.out.println(str.charAt(N % 3));
    }
}
