package kr.goldenmine.baekjoon.bronze.bronze5.p26082;

import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan =  new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        System.out.println(B / A * C * 3);
    }
}
