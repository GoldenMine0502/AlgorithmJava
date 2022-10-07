package kr.goldenmine.platinum.platinum4.p8112;

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
        int[] results = new int[10];

        for(int i = 0; i < 10; i++) {
            int mul = 1;
            while(true) {
                int result = i * mul;

                if(result % 10 == 0 || result % 10 == 1) {
                    break;
                }

                mul++;
            }

            results[i] = mul;
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(results[i]);
        }
    }
//    public static void main(String[] args) {
//        FastReader scan = new FastReader();
//
//        int T = scan.nextInt();
//
//        while(T-- > 0) {
//            int N = scan.nextInt();
//
//            // 0
//            // 1
//
//            // 01
//            // 00
//            // 11
//            // 10
//
//        }
//    }
}
