package kr.goldenmine.baekjoon.bronze.bronze2.p15829;

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

    public static long pow(long value, long exponent, long mod) {
        if(exponent == 0) return 1;
        if(exponent == 1) return value;
        if(exponent == 2) return (value * value) % mod;

        long half = pow(value, exponent / 2, mod) % mod;

        if(exponent % 2 == 0) {
            return (half * half) % mod;
        } else {
            return (((half * half) % mod) * value) % mod;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        final int N = scan.nextInt();
        final String str = scan.next();
        final long R = 31;
        final long mod = 1234567891;

        long[] rs = new long[51];
        for(int i = 0; i < 51; i++) {
            rs[i] = pow(R, i, mod);
        }

        long sum = 0;
        for(int i = 0; i < N; i++) {
            int a = str.charAt(i) - 'a' + 1;

            long result = (a * rs[i]) % mod;
            sum += result;
            sum %= mod;
        }

        System.out.println(sum);
    }
}
