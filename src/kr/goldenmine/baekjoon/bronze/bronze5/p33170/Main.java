package kr.goldenmine.baekjoon.bronze.bronze5.p33170;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        if(A + B + C <= 21) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}
