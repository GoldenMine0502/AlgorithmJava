package kr.goldenmine.baekjoon.bronze.bronze5.p24736;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            int e = sc.nextInt();
            System.out.print(a * 6 + b * 3 + c * 2 + d + e * 2);
        }
        System.out.print(" ");
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(a * 6 + b * 3 + c * 2 + d + e * 2);
        }
    }
}
