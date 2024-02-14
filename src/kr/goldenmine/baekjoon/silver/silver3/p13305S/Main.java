package kr.goldenmine.baekjoon.silver.silver3.p13305S;

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

        int[] distance = new int[N - 1];
        int[] cost = new int[N];


        for(int i = 0; i < N - 1; i++) {
            distance[i] = scan.nextInt();
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            cost[i] = scan.nextInt();

            if(min > cost[i]) {
                min = cost[i];
            }

            if(cost[i] > min) {
                cost[i] = min;
            }
        }
//        System.out.println(Arrays.toString(cost));

        long total = 0;
        for(int i = 0; i < N - 1; i++) {
            total += (long) cost[i] * distance[i];
        }

        System.out.println(total);
    }
}
