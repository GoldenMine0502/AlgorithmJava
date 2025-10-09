package kr.goldenmine.baekjoon.bronze.bronze5.p31429;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] sols = {12, 11, 11, 10, 9, 9, 9, 8, 7, 6, 6};
        int[] mins = {1600, 894, 1327, 1311, 1004, 1178, 1357, 837, 1055, 556, 773};

        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt() - 1;
        System.out.println(sols[N] + " " + mins[N]);
    }
}
