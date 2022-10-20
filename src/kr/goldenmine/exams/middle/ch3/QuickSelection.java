package kr.goldenmine.exams.middle.ch3;

import java.util.Arrays;
import java.util.Random;

public class QuickSelection {
    public static void main(String[] args) {
        // 숫자 배열
        int[] nums = { 69, 10, 30, 2, 16, 8, 31, 22 };

        // 결과 값 출력
        int result = quickSelect(nums, 0, nums.length - 1, 3);
        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }

    private static int quickSelect(final int[] NUMS, int start, int end, int k) {
        if (start <= end) {
            int pivot = partition(NUMS, start, end);

            if (pivot == k) {
                return NUMS[k];
            }

            else if (pivot > k) {
                return quickSelect(NUMS, start, pivot - 1, k);
            }

            else {
                return quickSelect(NUMS, pivot + 1, end, k);
            }
        }

        return Integer.MIN_VALUE;
    }

    private static int partition(final int[] NUMS, int start, int end) {
        int pivot = NUMS[end];

        System.out.println("(before) (" + start + ", " + end + ") pivot: " + pivot + ", " + Arrays.toString(NUMS));
//        swap(NUMS, end, pivot);
//        System.out.println("(swap) (" + start + ", " + end + ") pivot: " + pivot + ", " + Arrays.toString(NUMS));

        for (int i = start; i < end; i++) {
            if (NUMS[i] < pivot) {
                swap(NUMS, i, start);
                start++;
                System.out.println("(loop) (" + start + ", " + end + ") pivot: " + pivot + ", " + Arrays.toString(NUMS));
            }
        }

        swap(NUMS, start, end);
        System.out.println("(swap) (" + start + ", " + end + ") pivot: " + pivot + ", " + Arrays.toString(NUMS));

        return start;
    }

    /**
     * 배열의 두 요소의 값을 바꾸는 메소드
     *
     * @param nums
     * @param aIdx
     * @param bIdx
     */
    private static void swap(final int[] nums, final int aIdx, final int bIdx) {
        int tmp = nums[aIdx];
        nums[aIdx] = nums[bIdx];
        nums[bIdx] = tmp;
    }
}
