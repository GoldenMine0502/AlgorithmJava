package kr.goldenmine.baekjoon.silver.silver5.p1251;

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

        String text = scan.next();

        String result = null;

        for (int indexLeft = 1; indexLeft < text.length() - 1; indexLeft++) {
            for (int indexRight = indexLeft + 1; indexRight < text.length(); indexRight++) {
                String text1 = new StringBuilder(text.substring(0, indexLeft)).reverse().toString();
                String text2 = new StringBuilder(text.substring(indexLeft, indexRight)).reverse().toString();
                String text3 = new StringBuilder(text.substring(indexRight)).reverse().toString();

                String current = text1 + text2 + text3;

                if (result == null) {
                    result = current;
                } else {
                    // current가 result보다 사전상 앞서면 바꾸기
                    if (current.compareTo(result) < 0) {
                        result = current;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
