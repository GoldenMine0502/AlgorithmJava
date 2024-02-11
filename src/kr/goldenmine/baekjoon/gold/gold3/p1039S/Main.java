package kr.goldenmine.baekjoon.gold.gold3.p1039S;

import java.util.*;

public class Main {
    static int max = -1;
    static int limit = -1;

    static List<HashSet<Integer>> cached = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String value = scan.next();
        limit = scan.nextInt();

        int[] arr = new int[value.length()];

        for (int i = 0; i < value.length(); i++) {
            arr[i] = value.charAt(i) - '0';
        }

        for (int i = 0; i <= limit; i++) {
            cached.add(new HashSet<>());
        }

        int result = solve(arr, 0, 0, 0);
        System.out.println(result);
//        if (result)
//            System.out.println(max);
//        else
//            System.out.println(-1);
    }

    public static int solve(int[] previous, int depth, int p1, int p2) {
        // 복사 (이전 값에 영향 미치게 하지 않게 하기 위함)
        int[] current = new int[previous.length];

        for (int i = 0; i < previous.length; i++) {
            current[i] = previous[i];
        }

        if (depth > 0) {
            int temp = current[p1];
            current[p1] = current[p2];
            current[p2] = temp;

            // 0으로 시작하는 경우 -1
            if (current[0] == 0) return -1;

            // 같은 깊이에서 중복시 -1
            int duplicatedCheck = calculate(current);
            if (cached.get(depth).contains(duplicatedCheck)) {
                return -1;
            } else {
                cached.get(depth).add(duplicatedCheck);
            }
        }

        int max = -1;

        if (depth < limit) {
            for (int i = 0; i < current.length - 1; i++) {
                for (int j = i + 1; j < current.length; j++) {
                    int result = solve(current, depth + 1, i, j);

                    max = Math.max(max, result);
                }
            }
        } else {
            return calculate(current);
        }
        return max;
    }

    static int calculate(int[] arr) {
        int digit = 1;
        int sum = 0;

        // 자릿수 계산
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];

            sum += value * digit;

            digit *= 10;
        }

        return sum;
    }
}
