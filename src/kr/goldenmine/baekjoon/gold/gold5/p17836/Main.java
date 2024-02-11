package kr.goldenmine.baekjoon.gold.gold5.p17836;

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
        boolean mode;

        public Point(int x, int y, int depth, boolean mode) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.mode = mode;
        }

        public Point add(Point p, int depth, boolean mode) {
            return new Point(x + p.x, y + p.y, depth, mode);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", mode=" + mode +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0, -1, false),
            new Point(1, 0, -1, false),
            new Point(0, -1, -1, false),
            new Point(0, 1, -1, false),
    };

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int limit = scan.nextInt();

        int[][] arr = new int[Y][X];

        for(int y = 0; y < Y; y++) {
            for(int x = 0; x < X; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0, false));

        boolean[][][] visited = new boolean[Y][X][2];
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

//            System.out.println(current + ", " + arr[current.y][current.x]);

            if(current.x == X - 1 && current.y == Y - 1) {
                System.out.println(current.depth);
                return;
            }

            for(int i = 0; i < 4; i++) {
                Point next = current.add(directions[i], current.depth + 1, current.mode);

                if(next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if(arr[next.y][next.x] == 2) {
                        next.mode = true;
                    }

                    if(!visited[next.y][next.x][current.mode ? 1 : 0]) {
                        visited[next.y][next.x][current.mode ? 1 : 0] = true;

                        if(arr[next.y][next.x] == 0 || next.mode) {
                            if(next.depth <= limit) {
                                queue.add(next);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Fail");
    }
}
