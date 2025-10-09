package kr.goldenmine.baekjoon.bronze.bronze5.p5717;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            if(a == 0 && b == 0) break;
            System.out.println(a + b);
        }
    }
}
