package kr.goldenmine.baekjoon.silver.silver2.p4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(-1, -1),
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, -1),
            new Point(1, -1),
            new Point(0, 1),
            new Point(-1, 1),
    };

    public static void bfs(int X, int Y, int[][] arr, boolean[][] visited, Point start) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.x][start.y] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (arr[next.x][next.y] == 1) {
                        if (!visited[next.x][next.y]) {
                            visited[next.x][next.y] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        while (true) {
            int X = scan.nextInt();
            int Y = scan.nextInt();

            if (X == 0 && Y == 0) break;

            int[][] arr = new int[X][Y];

            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    arr[x][y] = scan.nextInt();
                }
            }

            boolean[][] visited = new boolean[X][Y];

            int count = 0;
            for (int x = 0; x < X; x++) {
                for (int y = 0; y < Y; y++) {
                    if (arr[x][y] == 1 && !visited[x][y]) {
                        bfs(X, Y, arr, visited, new Point(x, y));
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }
}
