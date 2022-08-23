package kr.goldenmine.silver.silver1.p2178;

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

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, depth + 1);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    public static int bfs(int X, int Y, int[][] arr, boolean[][] visited, Point start) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            if(p.x == X - 1 && p.y == Y - 1) {
                return p.depth + 1;
            }

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (arr[next.y][next.x] == 1) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
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

        for(int i = 0; i < Y; i++) {
            String line = scan.next();

            for(int j = 0; j < X; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        boolean[][] visited = new boolean[Y][X];

        int result = bfs(X, Y, arr, visited, new Point(0, 0));

        System.out.println(result);
    }
}
