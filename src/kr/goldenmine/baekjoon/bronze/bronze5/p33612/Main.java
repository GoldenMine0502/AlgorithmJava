package kr.goldenmine.baekjoon.bronze.bronze5.p33612;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        if(N == 1) {
            System.out.println("2024 8");
        } else if(N == 2) {
            System.out.println("2025 3");
        } else if(N == 3) {
            System.out.println("2025 10");
        } else if(N == 4) {
            System.out.println("2026 5");
        } else {
            System.out.println("2026 12");
        }
    }
}
