package kr.goldenmine.gold.gold5.p2212A;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        Integer[] diffs = new Integer[N - 1];
        for(int i = 0; i < N - 1; i++) {
            diffs[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diffs, Collections.reverseOrder());

        int sum = 0;

        for(int i = K - 1; i < N - 1; i++) {
            sum += diffs[i];
        }
        System.out.println(sum);
//        for(int i = 0; i < diffs.length; i++) {
//            System.out.println(diffs[i]);
//        }
    }
}

//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//
//        int N = scan.nextInt();
//        int K = scan.nextInt();
//
//        int[] arr = new int[N];
//        int[] walls = new int[K];
//
//        for(int i = 0; i < N; i++) {
//            arr[i] = scan.nextInt();
//        }
//
//        for(int i = 0; i < K; i++) {
//            walls[i] = i;
//        }
//
//
//    }
//}
