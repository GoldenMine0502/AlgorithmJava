package kr.goldenmine.bronze.bronze5.p2739;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        for(int i = 1; i <= 9; i++) {
            System.out.println(N + " * " + i + " = " + (i * N));
        }
    }
}
