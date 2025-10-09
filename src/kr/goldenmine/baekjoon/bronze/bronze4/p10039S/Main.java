package kr.goldenmine.baekjoon.bronze.bronze4.p10039S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        int N = scan.nextInt();
        int sum = 0;
        for(int i = 0; i < 5; i++) {
            sum += Math.max(scan.nextInt(), 40);
        }

        System.out.println(sum / 5);
    }
}
