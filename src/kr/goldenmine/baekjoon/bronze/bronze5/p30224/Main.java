package kr.goldenmine.baekjoon.bronze.bronze5.p30224;

import java.util.Scanner;

public class Main {

    static boolean check7Contain(int n) {
        while(n > 0) {
            if(n % 10 == 7) return true;
            n /= 10;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        boolean divide7 = N % 7 == 0;
        boolean contain7 = check7Contain(N);

        if(!divide7 && !contain7) System.out.println("0");
        if(divide7 && !contain7) System.out.println("1");
        if(!divide7 && contain7) System.out.println("2");
        if(divide7 && contain7) System.out.println("3");

    }
}
