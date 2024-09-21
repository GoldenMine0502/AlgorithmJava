package kr.goldenmine.baekjoon.bronze.bronze4.p21964;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        String str = scan.next();

        System.out.println(str.substring(str.length() - 5));
    }
}
/*
123
asdjksvas
 */
