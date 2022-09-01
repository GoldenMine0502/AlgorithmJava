package kr.goldenmine.bronze.bronze2.p2750;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = i; j >= 1; j--) {
                if(arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
