package kr.goldenmine.gold.gold5.p9251;

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
            newTokenizer();

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
            newTokenizer();

            String str = "";
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

        void newTokenizer() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        String a = scan.nextLine();
        String b = scan.nextLine();

        int bIndex = 0;
        int count = 0;

        int maxCount = 0;

        for(int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            bIndex = 0;
            count = 0;
            while(bIndex++ < b.length()) {
                while (bIndex < b.length() && b.charAt(bIndex++) != ch) {

                }
                if (bIndex < b.length()) {
                    count++;
                }
            }
            System.out.println(count);
        }

        System.out.println(maxCount);
    }
}
