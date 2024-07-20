package kr.goldenmine.baekjoon.bronze.bronze2.p18312S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();

        int count = 0;

        for(int hour = 0; hour <= N; hour++) {
            boolean containH = (hour % 10 == K) || (hour / 10 == K);

            if(containH) {
                count += 60 * 60;
                continue;
            }

            for(int minute = 0; minute < 60; minute++) {
                boolean containM = (minute % 10 == K) || (minute / 10 == K);

                if(containM) {
                    count += 60;
                    continue;
                }

                for(int second = 0; second < 60; second++) {
                    boolean containS = (second % 10 == K) || (second / 10 == K);

                    if(containH || containM || containS) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
