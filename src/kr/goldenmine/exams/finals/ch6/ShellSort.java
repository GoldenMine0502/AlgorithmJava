package kr.goldenmine.exams.finals.ch6;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] gaps = {5, 3, 1};

        int[] arr = {80, 85, 5, 95, 10, 35, 25, 90, 30, 60, 40, 75, 20};

        int n = arr.length;

        for (int gap : gaps)
        {
            for (int i = gap; i < n; i++)
            {
                int temp = arr[i];

                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                arr[j] = temp;
            }

            System.out.println("gap: " + gap + ", " + Arrays.toString(arr));
        }
//        for(int h : gap) {
//            for(int i = h; i < gap.length; i++) {
//                int currentElement = array[i];
//                int j = i;
//                while(j >= h && array[j - h] > currentElement) {
//                    array[j] = array[j - h];
//                    j = j - h;
//                }
//
//                array[j] = currentElement;
//            }
//        }

        System.out.println(Arrays.toString(arr));
    }
}
