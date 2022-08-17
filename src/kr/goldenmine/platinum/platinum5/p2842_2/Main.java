package kr.goldenmine.platinum.platinum5.p2842_2;

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
        int minus = 0;
        int plus = 0;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int minus, int plus) {
            this(x, y);
            this.minus = minus;
            this.plus = plus;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, minus, plus);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", minus=" + minus +
                    ", plus=" + plus +
                    '}';
        }
    }

    static class Difference {
        int minus;
        int plus;

        public Difference(int minus, int plus) {
            this.minus = minus;
            this.plus = plus;
        }

        @Override
        public String toString() {
            return "Difference{" +
                    "minus=" + minus +
                    ", plus=" + plus +
                    '}';
        }
    }

        static Point[] directions = new Point[]{
            new Point(-1, -1),
            new Point(-1, 0),
            new Point(-1, 1),
            new Point(0, -1),
            new Point(0, 1),
            new Point(1, -1),
            new Point(1, 0),
            new Point(1, 1),
    };

    public static void dfs(int X, int Y, int[][] arr, Difference[][] differences, Point start, Point point) {
        for(int i = 0; i < directions.length; i++) {
            Point next = point.add(directions[i]);


            if(next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {

                next.plus = Math.max(next.plus, arr[next.y][next.x] - arr[start.y][start.x]);
                next.minus = Math.min(next.minus, arr[next.y][next.x] - arr[start.y][start.x]);

                if (differences[next.y][next.x] == null) {
                    differences[next.y][next.x] = new Difference(next.minus, next.plus);
//                    System.out.println(next);
                    dfs(X, Y, arr, differences, start, next);
                } else if (next.plus < differences[next.y][next.x].plus) {
                    differences[next.y][next.x] = new Difference(Math.max(differences[next.y][next.x].minus, next.minus), Math.min(differences[next.y][next.x].plus, next.plus));
//                    System.out.println(next);
                    dfs(X, Y, arr, differences, start, next);
                } else if (next.minus > differences[next.y][next.x].minus) {
                    differences[next.y][next.x] = new Difference(Math.max(differences[next.y][next.x].minus, next.minus), Math.min(differences[next.y][next.x].plus, next.plus));
//                    System.out.println(next);
                    dfs(X, Y, arr, differences, start, next);
                }
            }
        }
    }

    public static void main(String[] args) {
                FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<Point> Ks = new ArrayList<>();

        int startX = -1;
        int startY = -1;

        char[][] types = new char[N][N];
        int[][] values = new int[N][N];

        for (int y = 0; y < N; y++) {
            String line = scan.next();
            for (int x = 0; x < N; x++) {
                types[y][x] = line.charAt(x);

                if (types[y][x] == 'P') {
                    startX = x;
                    startY = y;
                    Ks.add(new Point(x, y));
                }
                if (types[y][x] == 'K') {
                    Ks.add(new Point(x, y));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                values[y][x] = scan.nextInt();
            }
        }

        Difference[][] diffs = new Difference[N][N];
        diffs[startY][startX] = new Difference(0, 0);

        dfs(N, N, values, diffs, new Point(startX, startY), new Point(startX, startY));

//        for (int y = 0; y < N; y++) {
//            for (int x = 0; x < N; x++) {
//                System.out.print(diffs[y][x] + " ");
//            }
//            System.out.println();
//        }

        int min = 9999999;
        int max = -1;

        for (int i = 0; i < Ks.size(); i++) {
            Point k = Ks.get(i);
//            int difference = arr2[k.y][k.x] - arr2[startY][startX];
//            System.out.println(k + ", " + difference);

            max = Math.max(max, diffs[k.y][k.x].plus);
            min = Math.min(min, diffs[k.y][k.x].minus);
        }

        System.out.println(max - min);
    }
}
