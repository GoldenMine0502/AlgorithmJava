package kr.goldenmine.baekjoon.silver.silver1.p2178;

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

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        public Point add(Point p, int depth) {
            return new Point(x + p.x, y + p.y, depth);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0, 0),
            new Point(1, 0, 0),
            new Point(0, -1, 0),
            new Point(0, 1, 0),
    };

    public static int bfs(int X, int Y, int[][] arr, int[][] visited, Point start) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = 1;

        while (!points.isEmpty()) {
            Point p = points.poll();

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i], p.depth + 1);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if(arr[next.y][next.x] == 1) {
                        if (visited[next.y][next.x] == 0) {
                            visited[next.y][next.x] = visited[p.y][p.x] + 1;
                            points.add(next);
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] arr = new int[Y][X];

        for(int y = 0; y < Y; y++) {
            String line = scan.next();
            for(int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x) - '0';
            }
        }

        int[][] visited = new int[Y][X];
        bfs(X, Y, arr, visited, new Point(0, 0,  0));

        System.out.println(visited[Y - 1][X - 1]);
    }
}
