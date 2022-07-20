package kr.goldenmine.silver.silver3.p11051S;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[1001][1001];

        for(int i = 0; i <= 1000; i++) {
            arr[i][0] = 1;

            for(int j = 1; j <= i; j++) {
                arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j]) % 10007;
            }
        }

//        for(int i = 1; i <= 10; i++) {
//            for(int j = 0; j <= 10; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int K = scan.nextInt();

        System.out.println(arr[N][K]);
    }
}
