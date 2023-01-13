package kr.goldenmine.silver.silver1.p7562S;

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
            new Point(2, 1, 0),
            new Point(2, -1, 0),
            new Point(-2, 1, 0),
            new Point(-2, -1, 0),
            new Point(1, 2, 0),
            new Point(1, -2, 0),
            new Point(-1, 2, 0),
            new Point(-1, -2, 0),
    };

    public static int bfs(int X, int Y, boolean[][] visited, Point start, Point finish) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            if(p.x == finish.x && p.y == finish.y) {
                return p.depth;
            }

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i], p.depth + 1);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
//                    if (isQueueAdd.test(next)) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
//                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int L = scan.nextInt();

            boolean[][] visited = new boolean[L][L];

            Point p1 = new Point(scan.nextInt(), scan.nextInt(), 0);
            Point p2 = new Point(scan.nextInt(), scan.nextInt(), 0);

            int result = bfs(L, L, visited, p1, p2);

            System.out.println(result);
        }
    }
}
