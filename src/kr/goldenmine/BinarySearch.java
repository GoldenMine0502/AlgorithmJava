package kr.goldenmine;

import java.util.Arrays;

public class BinarySearch {
    public static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr[mid]) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    public static int upperBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    // 내림차순 기준
    // 완벽하게 같은 값 찾을 때
    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;

        while(true) {
            int mid = (left + right) / 2;
            if(arr[mid] == value) {
                return mid;
            } else if(arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if(left > right) return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 1, 4, 9};

        Arrays.sort(arr);

        int value = 9;

        System.out.println(lowerBound(arr, value) + ", " + upperBound(arr, value) + " = " + (upperBound(arr, value) - lowerBound(arr, value)));
    }
}
