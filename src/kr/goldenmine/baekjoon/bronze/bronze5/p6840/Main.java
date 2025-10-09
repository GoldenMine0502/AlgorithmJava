package kr.goldenmine.baekjoon.bronze.bronze5.p6840;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = {
                scan.nextInt(),
                scan.nextInt(),
                scan.nextInt(),
        };
        Arrays.sort(arr);
        System.out.println(arr[1]);
    }
}
