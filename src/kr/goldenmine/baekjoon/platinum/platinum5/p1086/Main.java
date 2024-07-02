package kr.goldenmine.baekjoon.platinum.platinum5.p1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    public static Point dfs(int[] arr, int visited) {
        return null;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        String[] arr = new String[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.next();
        }

        int K = scan.nextInt();
        BigInteger BigK = new BigInteger(String.valueOf(K));

        int[] numbers = new int[N];
        for(int i = 0; i < N; i++) {
            numbers[i] = new BigInteger(arr[i]).mod(BigK).intValue();
        }

        // D[s][i]: 현재까지 집합 s에 포함된 수를 이용했고, 그렇게 만들어진 수가 i일 때, 남은 수들을 이용하여 만들 수 있는 k로 나누어 떨어지는 수들의 개수
    }
}
