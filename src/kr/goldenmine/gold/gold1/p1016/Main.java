package kr.goldenmine.gold.gold1.p1016;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long min = scan.nextLong();
        long max = scan.nextLong();
        int diff = (int) (max - min + 1);

        // 이미 체크한 숫자인지 확인하는 배열. 0이면 체크안함, 1이면 체크함.
        // min 10, max 20인 경우 arr[0]은 10, arr[10]은 20.
        int[] arr = new int[diff];

        for(int i = 1; i < arr.length; i++) {
            arr[i] = 0;
        }

        int count = diff;

        // 제곱수 기준으로 백만이면 범위 안을 다 체크할 수 있다.
        // 제곱수로 나누어 떨어지는 친구가 있다면 카운트한다.
        // 안떨어지면 제곱 ㄴㄴ수.
        for(long i = 2; i <= 1000000; i++) {
            long squared = i * i;

            // max보다 크면 굳이..?
//            if(squared > max) break;

            // 4, 9, 16...
            // 초기 배열 인덱스 설정
//            long loop = min / squared;
//            loop *= squared;
//
//            while(loop < min) {
//                loop += squared;
//            }

            long loop = min;
            loop -= loop % squared;

            while(loop < min) {
                loop += squared;
            }

            // 에라토스테네스의 체
            while(loop <= max) {
                int arrIndex = (int) (loop - min);
                if(arr[arrIndex] == 0) {
                    count--;
                    arr[arrIndex] = 1;
//                    System.out.println(loop + ", " + arrIndex);
                }

                loop += squared;
            }
        }

        System.out.println(count);
    }
}
