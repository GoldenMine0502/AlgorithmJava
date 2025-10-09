package kr.goldenmine.baekjoon.bronze.bronze5.p28927;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sum1 = scan.nextInt() * 3 + scan.nextInt() * 20 + scan.nextInt() * 120;
        int sum2 = scan.nextInt() * 3 + scan.nextInt() * 20 + scan.nextInt() * 120;

        if(sum1 > sum2) {
            System.out.println("Max");
        } else if(sum1 < sum2) {
            System.out.println("Mel");
        } else {
            System.out.println("Draw");
        }
    }
}
