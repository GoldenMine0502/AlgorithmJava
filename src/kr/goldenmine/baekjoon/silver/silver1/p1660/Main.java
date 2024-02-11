package kr.goldenmine.baekjoon.silver.silver1.p1660;

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

        int[] shapes = new int[121];
        int sum = 0;
        for(int i = 1; i <= 120; i++) {
            int in = 0;
            for(int j = 1; j <= i; j++) {
                in += j;
            }
            sum += in;
//            System.out.println(sum + ", " + in);
            shapes[i] = sum;
        }

        int[] counts = new int[N + 1];
        Arrays.fill(counts, Integer.MAX_VALUE);
        counts[0] = 0;

        int[] sums = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= 120; j++) {
                int shape = shapes[j];
                if(i - shape < 0) break;

                if(sums[i] + shape <= N) {
                    counts[i] = Math.min(counts[i], counts[i - shape] + 1);
                }
            }
        }
        System.out.println(counts[N]);
    }
}
