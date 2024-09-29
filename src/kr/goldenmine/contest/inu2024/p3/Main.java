package kr.goldenmine.contest.inu2024.p3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[][] keyboard = new char[4][10];
        for(int y = 0; y < 4; y++) {
            String line = scan.next();
            for(int x = 0; x < 10; x++) {
                keyboard[y][x] = line.charAt(x);
            }
        }

        String input = scan.next();

        int[][] stroke = new int[4][10];

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            for(int y = 0; y < 4; y++) {
                for(int x = 0; x < 10; x++) {
                    if(keyboard[y][x] == ch) {
                        stroke[y][x] = 1;
                    }
                }
            }
        }

        for(int y = 1; y < 4 - 1; y++) {
            for(int x = 1; x < 10 - 1; x++) {
                if(
                        stroke[y - 1][x - 1] == 1 &&
                        stroke[y][x - 1] == 1 &&
                        stroke[y - 1][x] == 1 &&
                        stroke[y][x] == 1 &&
                        stroke[y + 1][x] == 1 &&
                        stroke[y][x + 1] == 1 &&
                        stroke[y + 1][x + 1] == 1 &&
                        stroke[y - 1][x + 1] == 1 &&
                        stroke[y + 1][x - 1] == 1
                ) {
                    System.out.println(keyboard[y][x]);
                }
            }
        }
    }
}
