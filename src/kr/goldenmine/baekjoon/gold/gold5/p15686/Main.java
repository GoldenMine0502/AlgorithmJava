package kr.goldenmine.baekjoon.gold.gold5.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
    }

//    public static int dfs(int N, List<Point> chickens, List<Point> houses) {
//        int
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[N][N];

        List<Point> chickens = new ArrayList<>();
        List<Point> houses = new ArrayList<>();
        // 예상 최대 시간복잡도: 1100만

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();

                if(arr[y][x] == 2) {
                    chickens.add(new Point(x, y));
                }

                if(arr[y][x] == 1) {
                    houses.add(new Point(x, y));
                }
            }
        }
    }
}
