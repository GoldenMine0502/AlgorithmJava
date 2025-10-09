package kr.goldenmine.baekjoon.bronze.bronze5.p29699;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str = "WelcomeToSMUPC";

        int N = scan.nextInt() - 1;

        System.out.println(str.charAt(N % 14));
    }
}
