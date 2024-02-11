package kr.goldenmine.baekjoon.platinum.platinum4.p9376;

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

    class Point {
        int x;
        int y;

    }

    static FastReader scan = new FastReader();

    public static void solve() {
        int Y = scan.nextInt();
        int X = scan.nextInt();

        char[][] arr = new char[Y][X];
        for(int y = 0; y < Y; y++) {
            String line = scan.next();
            for(int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x);
            }
        }

        // 둘이 따로 가는 경우
        // 만나는 경우
        // 두가지를 고려해서 풀어야 하지 않을까


    }

    public static void main(String[] args) {
        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            solve();
        }
    }
}
