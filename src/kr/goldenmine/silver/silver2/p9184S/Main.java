package kr.goldenmine.silver.silver2.p9184S;

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

    public static long recursive(long[][][] cache, int a, int b, int c) {
        if(cache[a + 50][b + 50][c + 50] > 0) return cache[a + 50][b + 50][c + 50];

        if(a <= 0 || b <= 0 || c <= 0) return cache[a + 50][b + 50][c + 50] = 1;

        if(a > 20 || b > 20 || c > 20) return cache[a + 50][b + 50][c + 50] = recursive(cache, 20, 20, 20);

        if(a < b && b < c)
            return cache[a + 50][b + 50][c + 50] =
                    recursive(cache, a, b, c - 1) +
                    recursive(cache, a, b - 1, c - 1) -
                    recursive(cache, a, b - 1, c);

        return cache[a + 50][b + 50][c + 50] =
                recursive(cache, a - 1, b, c) +
                recursive(cache, a - 1, b - 1, c) +
                recursive(cache, a - 1, b, c - 1) -
                recursive(cache, a - 1, b - 1, c - 1);
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int a, b, c;

        long[][][] cache = new long[101][101][101];

        while(true) {
            a = scan.nextInt();
            b = scan.nextInt();
            c = scan.nextInt();

            if(a != -1 || b != -1 || c != -1) {
                System.out.println("w(" + a + ", " + b + ", " + c + ") = " + recursive(cache, a, b, c));
            } else {
                break;
            }
        }
    }
}
