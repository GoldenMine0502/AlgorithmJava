package kr.goldenmine.codeforce.c925_div3.p1;

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

    public static void find(int value) {
        int min = Integer.MAX_VALUE;
        int minA = 0;
        int minB = 0;
        int minC = 0;

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                for(int k = 0; k < 26; k++) {
                    int sum = i + j + k + 3;
                    if(sum == value && min > sum) {
                        min = sum;
                        minA = i;
                        minB = j;
                        minC = k;
                    }
                }
            }
        }

        System.out.print((char)(minA + 'a'));
        System.out.print((char)(minB + 'a'));
        System.out.print((char)(minC + 'a'));
        System.out.println();
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        for(int i = 0; i < N; i++) {
            int value = scan.nextInt();
            find(value);
        }
    }
}
