package kr.goldenmine.baekjoon.bronze.bronze2.p10809;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.next();

        int[] arr = new int[26];
        for(int i = 0; i < 26; i++) {
            arr[i] = -1;
        }

        for(int i = text.length() - 1; i >= 0; i--) {
            char ch = text.charAt(i);

            // 'a' = 97 - 97 = 0 -> 배열의 첫번째 인덱스
            // 'b' = 98 - 97 = 1 -> 배열의 두번째 인덱스
            // 'c' = 99
            // 0~25
//            if(arr[ch - 'a'] == -1) {
                arr[ch - 'a'] = i;
//            }
        }

        for(int i = 0; i < 26; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
