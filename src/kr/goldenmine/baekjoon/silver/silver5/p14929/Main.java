package kr.goldenmine.baekjoon.silver.silver5.p14929;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }

        long[] sums = new long[N];
        for(int i = 0; i < N; i++) {
            sums[i] = (i > 0 ? sums[i - 1] : 0) + arr[i];
        }

//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(sums));

        long total = 0;
        for(int i = 0; i < N; i++) {
            total += arr[i] * (sums[N - 1] - sums[i]);
        }
        System.out.println(total);
        // -2 + 3 + -6
        // x_12 + x_13 + x_14
        // x_23 + x_24
        // x_34
    }
}
