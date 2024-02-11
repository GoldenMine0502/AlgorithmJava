package kr.goldenmine.baekjoon.silver.silver2.p1699A_dp;

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

        // cache[1] = 제곱수의 갯수
        int[] cache = new int[N + 1];

        for(int i = 0; i < N + 1; i++) {
            cache[i] = i; // 1^2 가 n개
        }

        for(int i = 0; i < N + 1; i++) {

            for(int j = 1; j * j <= i; j++) {
                if(j*j == i) {
                    cache[i] = 1;
                    break;
                } else {
                    cache[i] = Math.min(cache[i], cache[i - j * j] + 1);
                }
            }
        }

        System.out.println(cache[N]);
    }
}
