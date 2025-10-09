package kr.goldenmine.baekjoon.bronze.bronze5.p29863;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();
        if(A > B) B += 24;

        System.out.println(B - A);
    }
}
