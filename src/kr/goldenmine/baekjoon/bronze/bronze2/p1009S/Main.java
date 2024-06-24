package kr.goldenmine.baekjoon.bronze.bronze2.p1009S;

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

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            int res = mul(a, b) % 10;

            if(res == 0) {
                sb.append(1);
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }

    public static int mul(int a, int b) {
//        System.out.println(a + "^" + b);
        if(b >= 3) {
            int res = mul(a, b / 2) % 10;
//            System.out.println(res);
            if(b % 2 == 0) {
                return res * res;
            } else {
                return res * res * a;
            }
        } else if(b == 2) {
            return a * a;
        } else if(b == 1) {
            return a;
        } else {
            return 1;
        }
    }
}
