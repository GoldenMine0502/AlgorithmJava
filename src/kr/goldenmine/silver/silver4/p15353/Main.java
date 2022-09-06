package kr.goldenmine.silver.silver4.p15353;

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

        String s1 = new StringBuilder(scan.next()).reverse().toString();
        String s2 = new StringBuilder(scan.next()).reverse().toString();

        int[] arr = new int[s1.length() + s2.length()];
        int up = 0;

        for(int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - '0';

            for(int j = 0; j < s2.length(); j++) {
                int b = s2.charAt(i) - '0';

                int c = a * b + up;
                if (c >= 10) {
                    c -= 10;
                    up = 1;
                } else {
                    up = 0;
                }
            }
        }

//        if(up == 1) {
//            result += "1";
//        }
//
//        System.out.println(new StringBuilder(result).reverse().toString());
    }
}
