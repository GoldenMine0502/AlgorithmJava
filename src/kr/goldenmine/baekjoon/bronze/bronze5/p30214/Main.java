package kr.goldenmine.baekjoon.bronze.bronze5.p30214;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();

        if(A * 2 >= B) {
            System.out.println("E");
        } else {
            System.out.println("H");
        }
    }
}
