package kr.goldenmine.silver.silver2.p2805S;

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

    public static long count(long[] arr, long value) {
        long sum = 0;

        for (long j : arr) {
            sum += Math.max(0, (j - value));
        }

        return sum;
    }

    public static long upperBound(long[] arr, int N, long max) {
        long left = 0;
        long right = max + 1;

        // lo가 hi랑 같아질 때 까지 반복
        while (left < right) {
            long mid = (left + right) / 2; // 중간위치를 구한다.
            if(mid == 0) return 0;

            long count = count(arr, mid);

            // key값이 중간 위치의 값보다 작을 경우
            if (count < N) {
                right = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                left = mid + 1;
            }

//            System.out.println(left + ", " + mid + ", " + right + ", " + count);
        }

        return left - 1;
    }


    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        long[] arr = new long[N];
        long max = -1;

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            max = Math.max(arr[i], max);
        }

        long length = upperBound(arr, M, max);

        System.out.println(length);
    }
}
