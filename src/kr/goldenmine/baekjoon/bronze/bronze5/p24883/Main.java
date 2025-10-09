package kr.goldenmine.baekjoon.bronze.bronze5.p24883;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char ch = (new Scanner(System.in)).next().charAt(0);

        if(ch == 'N' || ch == 'n') System.out.println("Naver D2");
        else System.out.println("Naver Whale");
    }
}
