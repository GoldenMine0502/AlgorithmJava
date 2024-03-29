package kr.goldenmine.baekjoon.silver.silver2.p11053S;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        //[N] -> 지금까지의 최대 길이
        int[] cache = new int[N];

        for(int i = 0; i < N; i++) {
            int current = arr[i];

//            int maxSequence = 0;
            int maxCache = 0;

            for (int j = 0; j < i; j++) {
                int previous = arr[j];

                if (current > previous) {
                    if (maxCache < cache[j]) {
                        maxCache = cache[j];
//                        maxSequence = cache[j];
                    }
                }
            }

            cache[i] = maxCache + 1;
        }

        for(int i = 0; i < N; i++) {
            System.out.println(cache[i]);
        }

//        System.out.println(cache[N - 1]);
//        int max = -1;
//        for(int i = 0; i < N; i++) {
//            max = Math.max(max, cache[i]);
//        }
//
//        System.out.println(max);
    }
}
