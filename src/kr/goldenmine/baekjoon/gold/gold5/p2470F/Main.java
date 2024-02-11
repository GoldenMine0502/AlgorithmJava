package kr.goldenmine.baekjoon.gold.gold5.p2470F;

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

    public static int binarySearch(long[] arr, long value) {
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        long[] arr = new long[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        int[] results = new int[N];
        long minimum = Long.MAX_VALUE;

        int minimumIndex1 = -1;
        int minimumIndex2 = -1;

        for(int i = 0; i < N; i++) {
            long minus = -arr[i];
            int index = binarySearch(arr, minus);
            long min = Math.abs(arr[i] + arr[index]);
            int minIndex = index;

            if(index >= 1) {
                long min2 = Math.abs(arr[i] + arr[index - 1]);
                if(min2 < min) {
                    min = min2;
                    minIndex = index - 1;
                }
            }
            if(index < N - 1) {
                long min2 = Math.abs(arr[i] + arr[index + 1]);
                if(min2 < min) {
                    min = min2;
                    minIndex = index + 1;
                }
            }

//            System.out.println(arr[i] + ", " + index + ", " + min);

            if(min < minimum) {
                minimum = min;
                minimumIndex1 = i;
                minimumIndex2 = minIndex;
            }
        }

        if(arr[minimumIndex1] < arr[minimumIndex2]) {
            System.out.println(arr[minimumIndex1] + " " + arr[minimumIndex2]);
        } else {
            System.out.println(arr[minimumIndex2] + " " + arr[minimumIndex1]);
        }
    }
}
