package kr.goldenmine.baekjoon.bronze.bronze3.p2566;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] arr = new int[9][9];
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                arr[y][x] = scanner.nextInt();
            }
        }

        int max = arr[0][0];
        int maxY = 0;
        int maxX = 0;
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                if(arr[y][x] > max) {
                    max = arr[y][x];
                    maxY = y;
                    maxX = x;
                }
            }
        }

        System.out.println(max);
        System.out.println((maxY + 1) + " " + (maxX + 1));
    }
}
