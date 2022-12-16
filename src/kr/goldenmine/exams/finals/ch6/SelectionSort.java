package kr.goldenmine.exams.finals.ch6;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {90, 30, 50, 20, 40, 10, 80, 60, 70};

        for (int i = array.length - 1; i > 0; i++) {
            int minIndex = i;
            for (int j = i - 1; j >= 0; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

            System.out.println(Arrays.toString(array));
        }

//        System.out.println(Arrays.toString(array));
    }
}
