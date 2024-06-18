package kr.goldenmine.baekjoon.silver.silver2.p18222;

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

        /*
        0
        01
        0110
        01101001
        0110100110010110
         */
        long N = scan.nextLong() - 1;

        long index = 1;
        while(index * 2 <= N) {
            index *= 2;
        }
//        System.out.println(index + ", " + N);

        int count = 0;
        while(N >= 1) {
            if(N - index >= 0) {
                N -= index;
                count++;
            }
            index /= 2;

//            System.out.println(index + ", " + N);
        }
        // 9 ->
        System.out.println(count % 2);
    }
}
