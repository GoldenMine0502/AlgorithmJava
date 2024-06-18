package kr.goldenmine.baekjoon.gold.gold4.p2015A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        int K = scan.nextInt();

        int[] Ns = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            Ns[i] = scan.nextInt();
        }

        HashMap<Integer, Long> map = new HashMap<>();
        long count = 0;
        int[] sum = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Ns[i];
            if(sum[i] == K) { count++; }
        }

        for(int i = 1; i <= N; i++) {
            count += map.getOrDefault(sum[i] - K, 0L);
            map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1L);
            System.out.println(count + ", " + map);
        }

        System.out.println(count);
    }
}
