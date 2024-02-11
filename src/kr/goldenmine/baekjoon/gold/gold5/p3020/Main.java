package kr.goldenmine.baekjoon.gold.gold5.p3020;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt(); // width
        int H = scan.nextInt(); // height

        int[] ceil = new int[N % 2 == 0 ? N / 2 : N / 2 - 1];
        int[] floor = new int[N / 2];
        for(int i = 0; i < N; i++) {
            int value = scan.nextInt();

            if(i % 2 == 0) {
                floor[i / 2] = value;
            } else {
                ceil[i / 2] = value;
            }
        }

        Arrays.sort(floor);
        Arrays.sort(ceil);

//        System.out.println(Arrays.toString(ceil));
//        System.out.println(Arrays.toString(floor));

        int[] results = new int[H];
        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= H; i++) {
//            System.out.println(i + ", " + (H - i + 1) + ", " + (floor.length - lowerBound(floor, i)) + ", " + (ceil.length - lowerBound(ceil, H - i + 1)));
            results[i - 1] = (floor.length - lowerBound(floor, i)) + (ceil.length - lowerBound(ceil, H - i + 1));

            min = Math.min(min, results[i - 1]);
        }

        Arrays.sort(results);
//        System.out.println(Arrays.toString(results));

        int count = upperBound(results, min) - lowerBound(results, min);

        System.out.println(min + " " + count);
    }
}
