package kr.goldenmine.baekjoon.gold.gold2.p1300A_binary_bound_k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

//    public static int binarySearch(int[] arr, int value) {
//        int left = 0;
//        int right = arr.length - 1;
//
//        while(true) {
//            int mid = (left + right) / 2;
//            if(arr[mid] == value) {
//                return mid;
//            } else if(arr[mid] > value) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//
//            if(left > right) return -1;
//        }
//    }

    public static long lowerBound(int N, int K) {
        long lo = 1;
        long hi = K;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            long mid = (lo + hi) / 2; // 중간위치를 구한다.
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (K <= count) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int k = scan.nextInt();

        System.out.println(lowerBound(N, k));
    }
}