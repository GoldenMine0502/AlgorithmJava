package kr.goldenmine.contest.p986.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    static int smallestSubWithSum(int[] arr, int n, int x) {
        // Initialize current sum and minimum length
        int curr_sum = 0, min_len = n + 1;

        // Initialize starting and ending indexes
        int start = 0, end = 0;
        while (end < n) {
            // Keep adding array elements while current sum
            // is smaller than or equal to x
            while (curr_sum <= x && end < n)
                curr_sum += arr[end++];

            // If current sum becomes greater than x.
            while (curr_sum > x && start < n) {
                // Update minimum length if needed
                if (end - start < min_len)
                    min_len = end - start;
                return end;

                // remove starting elements
//                curr_sum -= arr[start++];
            }
        }
        return -1;
    }

    static int sumSubarrayMins(int[] arr, int n)
    {
        int dp[] = new int[n];
        for (int i = 0; i < n; i++)
            dp[i] = 0;

        // calculate right smaller element
        int right[] = new int[n];

        for (int i = 0; i < n; i++) {
            right[i] = i;
        }
        ArrayList<Integer> stack = new ArrayList<Integer>();
        stack.add(0);

        for (int i = 1; i < n; i++) {

            int curr = arr[i];
            while (
                    (stack.size() > 0)
                            && (curr
                            < arr[stack.get(stack.size() - 1)])) {

                int idx = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                right[idx] = i;
            }
            stack.add(i);
        }

        dp[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            int right_idx = right[i];
            if (right_idx == i) { // case 1

                int curr = (n - i) * arr[i];
                dp[i] = curr;
            }

            else { // case 2

                // sum upto next smaller rhs element
                int upto_small = (right_idx - i) * (arr[i]);

                int curr_sum = (upto_small + dp[right_idx]);
                dp[i] = curr_sum;
            }
        }

        // calculating sum of dp
        int sum = 0;

        for (int i = 0; i < dp.length; i++)
            sum += dp[i];

        System.out.println(Arrays.toString(dp));

        return sum;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = scan.nextInt();
        while (T-- > 0) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int V = scan.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = scan.nextInt();
            }

            System.out.println(sumSubarrayMins(arr, N));
        }
    }
}

/*

            int N = scan.nextInt();
            int M = scan.nextInt();
            int V = scan.nextInt();

            int[] arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = scan.nextInt();
            }

            long[] prefixSum = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            List<Long> ints = new ArrayList<>();
//            List<Long> remaining = new ArrayList<>();
            int left = 1;
            int right = 1;
            while (left <= N && right <= N) {
                long sum = prefixSum[right] - prefixSum[left - 1];

                if (sum < V) {
                    right++;
                } else {
                    int nextLeft = left + 1;
                    while(nextLeft <= right && (prefixSum[right] - prefixSum[nextLeft - 1]) >= V) {
                        nextLeft++;
                    }
                    nextLeft--;
                    ints.add(prefixSum[right] - prefixSum[nextLeft - 1]);
                    if(left < nextLeft) {
                        ints.add(prefixSum[nextLeft - 1] - prefixSum[left - 1]);
                    }
//                    System.out.println(nextLeft + ", " + right);
                    right++;
                    left = right;
//                    left = right;
                }
            }
//            Collections.sort(ints);
//            Collections.sort(remaining);
            System.out.println("====");
            System.out.println(ints);
//            System.out.println(remaining);

//            System.out.println(ints.get(N - M));
//            if (right <= N) {
//                ints.add(prefixSum[N] - prefixSum[left - 1]);
//            }
//            ints.add()
//            max = Math.max(max, prefixSum[N] - prefixSum[left - 1]);
//            if (ints.size() <= M) {
//                System.out.println(ints);
//            }
 */
