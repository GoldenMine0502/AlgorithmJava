package kr.goldenmine.baekjoon.bronze.bronze5.p32314;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int A = scan.nextInt();
        int B = scan.nextInt();

        if(A / B >= N) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}
