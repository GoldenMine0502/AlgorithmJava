package kr.goldenmine.baekjoon.gold.gold3.p20440;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        Point[] points = new Point[N];
        for(int i = 0; i < N; i++) {
            points[i] = new Point(scan.nextInt(), scan.nextInt());
        }

        Arrays.sort(points, (o1, o2) -> {
            int x = Integer.compare(o1.x, o2.x);
            if (x == 0) {
                return Integer.compare(o1.y, o2.y);
            }
            return x;
        });
        /*
3
2 16
4 6
6 10
[Point{x=2, y=16}, Point{x=4, y=6}, Point{x=6, y=10}]

4
2 16
4 6
6 10
7 8
[Point{x=2, y=16}, Point{x=4, y=6}, Point{x=6, y=10}, Point{x=7, y=8}]


4
2 16
4 6
6 10
5 9
[(2, 16), (4, 6), (5, 9), (6, 10)]
         */

        System.out.println(Arrays.toString(points));
    }
}
