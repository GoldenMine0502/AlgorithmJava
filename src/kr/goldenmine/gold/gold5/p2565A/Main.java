package kr.goldenmine.gold.gold5.p2565A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

        int[][] wire = new int[N][2];
        Integer[] cache = new Integer[N];

        for(int i = 0; i < N; i++) {
            wire[i][0] = scan.nextInt();
            wire[i][1] = scan.nextInt();
        }

        Arrays.sort(wire, Comparator.comparingInt(o -> o[0]));

        for(int i = 0; i < N; i++) {
            cache[i] = 1;
            for(int j = 0; j < i; j++) {
                if(wire[i][1] > wire[j][1]) {
                    cache[i] = Math.max(cache[i], cache[j] + 1);
                }
            }
        }

        int max = 0;

        for(int i = 0; i < N; i++) {
            max = Math.max(cache[i], max);
        }

        // 전선 개수 - 쵀댓값
        System.out.println(N - max);
    }
}
