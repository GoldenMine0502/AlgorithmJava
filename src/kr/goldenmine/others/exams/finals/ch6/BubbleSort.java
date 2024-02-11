package kr.goldenmine.others.exams.finals.ch6;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {1, 2, 5, 4, 4};

        for(int pass = 0; pass < array.length - 1; pass++) {
            int count = 0;
            for(int i = 1; i < array.length - pass; i++) {
                if(array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;

                    count++;
                }
            }
            System.out.println(count + ", " + Arrays.toString(array));
            if(count == 0) break;
        }

    }
}
