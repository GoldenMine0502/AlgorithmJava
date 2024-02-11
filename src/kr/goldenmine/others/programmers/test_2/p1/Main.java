package kr.goldenmine.others.programmers.test_2.p1;

import java.util.*;

public class Main {

    // 20 50 0 50
    public int solution(int[] people, int limit) {
        // 20, 20, 30, 40, 50, 60 -> 20 20, 30 40, 50, 60
        // 30, 40, 80, 50, 100, 20 ->
        // 20, 30, 70, 80

        Arrays.sort(people);

        int[] counts = new int[201]; // counts[0] = 40kg, counts[200] = 240kg

        for(int i = 0; i < people.length; i++) {
            counts[people[i] - 40]++;
        }

        int total = 0;

        // 두명씩 묶기
        for(int i = 0; i <= 200; i++) {
            int leftWeight = i + 40;

            if(counts[i] > 0) {
                for (int j = 200; j >= i; j--) {
                    int rightWeight = j + 40;

                    if (leftWeight + rightWeight <= limit) {
                        int min = Math.min(counts[i], counts[j]);
                        if (i == j) min /= 2;

                        counts[i] -= min;
                        counts[j] -= min;
                        total += min;

//                        System.out.println(leftWeight + ", " + rightWeight + ", " + min + ", " + total + ", " + counts[i] + ", " + counts[j]);

                        if(counts[i] == 0) break;
                    }
                }
            }
        }

        // 한명씩 묶기
        for(int i = 0; i <= 200; i++) {
            total += counts[i];
        }

        return total;
    }

    public static void main(String[] args) {
        int[] people = {40, 50, 60, 60};
        int limit = 110;

        System.out.println(new Main().solution(people, limit));
    }
}
