package kr.goldenmine.baekjoon.bronze.bronze5.p26209;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean c = false;
        for(int i = 0; i < 8; i++) {
            int n = scan.nextInt();

            if(n == 9) {
                c = true;
            }
        }
        if(!c) {
            System.out.println("S");
        } else {
            System.out.println("F");
        }
    }
}
