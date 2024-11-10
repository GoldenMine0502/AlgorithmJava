package kr.goldenmine.contest.p986.C;

import java.util.ArrayList;
import java.util.Arrays;

public class CTest {
    // Function to calculate the count
    // of subarrays with a sum greater
    // than or equal to the target sum 'K'.
    public static int subarraySumgreaterThanEqualToK(ArrayList<Integer> arr, int K) {
        // Initialize variables to keep
        // track of the count of subarrays
        // and the current sum.
        int count = 0, sum = 0;

        // Get the size of the array.
        int n = arr.size();
        // Initialize two pointers 'l'
        // and 'r' to define the
        // current subarray.
        int l = 0, r = 0;

        // Iterate through the array.
        while (r < n) {
            // Add the current
            // element to the sum.
            sum += arr.get(r);

            // Adjust 'l' to maintain the
            // sum greater than or equal to 'K'.
            while (sum > K && l <= r) {
                sum -= arr.get(l++);
            }

            // Update the count by the
            // length of the current subarray.
            count += (r - l + 1);

            // Move to the
            // next element.
            r++;
        }
        // Return the final count of
        // subarrays with sum greater
        // than or equal to 'K'.
        return count;
    }

    // Function to calculate
    // the count of subarrays
    // with sum equal to 'k'.
    public static int subArraySum(ArrayList<Integer> nums, int k) {
        // Calculate the count of
        // subarrays with sum greater
        // than or equal to 'k'.
        int countK = subarraySumgreaterThanEqualToK(nums, k);
        // Calculate the count of
        // subarrays with sum greater
        // than or equal to 'k-1'.
        int countKMinus1 = subarraySumgreaterThanEqualToK(nums, k - 1);
        // Return the difference between the
        // two counts, representing the count
        // of subarrays with sum equal to 'k'.
        return countK - countKMinus1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 1, 10, 1, 1, 10));
        int k = 12;

        int count = subArraySum(arr, k);

        System.out.print("Input Array: ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
        System.out.println("Number of subarrays with sum equal " + k + ": " + count);
    }
}
