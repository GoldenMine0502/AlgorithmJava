package kr.goldenmine.baekjoon.gold.gold5.p2470_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";

            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

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
            } else {
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

    public static int binarySearch(int[] arr, long value) {
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

            if(left >= right) return left;
        }
    }

    // 같은 이진탐색, 다른 풀이: https://aig2029.tistory.com/346
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;

        for(int i = 0; i < N; i++) {
            int pairIndex = binarySearch(arr, -arr[i]);

            int valueM1 = pairIndex > 0 ? Math.abs(arr[i] + arr[pairIndex - 1]) : Integer.MAX_VALUE;
            int value = Math.abs(arr[i] + arr[pairIndex]);
            int valueP1 = pairIndex < N - 1 ? Math.abs(arr[i] + arr[pairIndex + 1]) : Integer.MAX_VALUE;

            if(i != pairIndex - 1) {
                if(valueM1 < min) {
                    index1 = i;
                    index2 = pairIndex - 1;
                    min = valueM1;
                }
            }

            if(i != pairIndex) {
                if(value < min) {
                    index1 = i;
                    index2 = pairIndex;
                    min = value;
                }
            }

            if(i != pairIndex + 1) {
                if(valueP1 < min) {
                    index1 = i;
                    index2 = pairIndex + 1;
                    min = valueP1;
                }
            }

//            System.out.println(arr[i] + ", " + pairIndex + ", " + valueM1 + ", " + value + ", " + valueP1);
        }

        if(arr[index1] < arr[index2]) {
            System.out.println(arr[index1] + " " + arr[index2]);
        } else {
            System.out.println(arr[index2] + " " + arr[index1]);
        }
    }
}
