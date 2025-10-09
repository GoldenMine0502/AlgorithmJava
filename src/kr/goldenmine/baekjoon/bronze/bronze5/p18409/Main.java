package kr.goldenmine.baekjoon.bronze.bronze5.p18409;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        String B = scan.next();

        int c = 0;
        for(int i = 0; i < B.length(); i++) {
            char ch = B.charAt(i);

            if(ch == 'a' || ch == 'e' || ch =='i' || ch =='o' || ch == 'u') {
                c++;
            }
        }
        System.out.println(c);
    }
}
