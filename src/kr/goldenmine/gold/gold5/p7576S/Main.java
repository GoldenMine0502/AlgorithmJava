package kr.goldenmine.gold.gold5.p7576S;

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
        int depth;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int depth) {
            this(x, y);
            this.depth = depth;
        }

        public Point add(Point p, int depth) {
            return new Point(x + p.x, y + p.y, depth);
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[M][N];

        Queue<Point> points = new LinkedList<>();

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt() - 1;
                if (arr[y][x] == 0) {
                    points.add(new Point(x, y, 0));
                }
            }
        }

        Point[] directions = new Point[]{
                new Point(-1, 0, -1),
                new Point(1, 0, -1),
                new Point(0, -1, -1),
                new Point(0, 1, -1),
        };

        boolean[][] visited = new boolean[M][N];

        int max = 0;

        while (!points.isEmpty()) {
            Point p = points.poll();
            max = Math.max(max, p.depth);

            arr[p.y][p.x] = p.depth;

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i], p.depth + 1);
                if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < M) {
                    if (arr[next.y][next.x] == -1) {
                        if (!visited[next.y][next.x]) {
                            points.add(next);
                            visited[next.y][next.x] = true;
                        }
                    }
                }
            }
        }

        boolean succeed = true;

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (arr[y][x] == -1) {
                    succeed = false;
                }
            }
        }

        if (succeed) {
            System.out.println(max);
        } else {
            System.out.println(-1);
        }
    }
}

