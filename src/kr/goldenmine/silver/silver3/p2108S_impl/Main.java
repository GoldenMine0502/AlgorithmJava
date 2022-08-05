package kr.goldenmine.silver.silver3.p2108S_impl;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        long sum = 0;

        for(int i = 0; i < N; i++) {
            sum += arr[i];
        }

        int average = (int) Math.round((double)sum / N);

        int medium;

        if(N % 2 == 1) {
            // N = 3
            // 1, 2, 3
            medium = arr[N / 2];
        } else {
            medium = (int) Math.round((arr[N / 2] + arr[N / 2 + 1]) / 2D);
        }

        int[] frequency = new int[9000];
        int constant = 4100;
        int unknown = 10000;
        int maxFrequency = 0;
        int maxFrequencyValue = unknown;
        int maxFrequencyValue2 = unknown;

        for(int i = 0; i < N; i++) {
            frequency[arr[i] + constant]++;

            int freq = frequency[arr[i] + constant];

            //
            if(freq > maxFrequency) {
                maxFrequency = freq;
                maxFrequencyValue = arr[i];
                maxFrequencyValue2 = unknown;
            } else if(freq == maxFrequency) {
                if(maxFrequencyValue < arr[i]) {
                    if(maxFrequencyValue2 < arr[i]) { // 이 경우 의미 없어진다
                        // continue;
                    } else { // 이 경우 두번째로 작은 값이므로
                        maxFrequencyValue2 = arr[i];
                    }
                } else { // 가장 작은 최빈값이므로 밀기
                    maxFrequencyValue2 = maxFrequencyValue;
                    maxFrequencyValue = arr[i];
                }
            }

//            System.out.println(freq + ", " + maxFrequency + ", " + maxFrequencyValue + ", " + maxFrequencyValue2);
        }

        if(maxFrequencyValue2 == unknown) { // 그냥 출력용으로 업데이트
            maxFrequencyValue2 = maxFrequencyValue;
        }

        int max = -4001;
        int min = 4001;

        for(int i = 0; i < N; i++) {
            if(arr[i] < min) {
                min = arr[i];
            }
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        int range = Math.abs(max - min);

        System.out.println(average);
        System.out.println(medium);
        System.out.println(maxFrequencyValue2);
        System.out.println(range);
    }
}
