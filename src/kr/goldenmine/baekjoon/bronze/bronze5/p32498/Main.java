package kr.goldenmine.baekjoon.bronze.bronze5.p32498;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int c = 0;

        for(int i = 0; i < N; i++) {
            if(scan.nextInt() % 2 == 1) {
                c++;
            }
        }
        System.out.println(c);
    }
}
