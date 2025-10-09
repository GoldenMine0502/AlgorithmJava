package kr.goldenmine.baekjoon.bronze.bronze5.p27291;

import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A = scan.nextInt();
        int B = scan.nextInt();

        if(B == 0 && (12 <= A && A <= 16)) {
            System.out.println(320);
        } else {
            System.out.println(280);
        }
    }
}
