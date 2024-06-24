package kr.goldenmine.baekjoon.bronze.bronze1.p1032S;

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

        char[] arr = scan.next().toCharArray();
        for(int i = 1; i < N; i++) {
            String text = scan.next();

            for(int j = 0; j < text.length(); j++) {
                if(arr[j] != text.charAt(j)) {
                    arr[j] = '?';
                }
            }
        }

        StringBuilder sb = new StringBuilder(arr.length);
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
