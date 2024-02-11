package kr.goldenmine.others.exams.finals.ch6;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {90, 30, 50, 20, 40, 10, 80, 60, 70};

        for(int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > currentElement) {
                array[j + 1] = array[j];
                j--;

//                System.out.println(Arrays.toString(array));
            }
            array[j + 1] = currentElement;

            System.out.println((i + 1) + ", " + (j + 2) + ", " + Arrays.toString(array));
        }
    }
}
