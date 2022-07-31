package kr.goldenmine.gold.gold4.p2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[N][M];

        Queue<Point> points = new LinkedList<>();
//        List<Point> breakablePoints = new ArrayList<>();

        for(int y = 0; y < N; y++) {
            String[] split = scan.next().split("");
            for(int x = 0; x < M; x++) {
                arr[y][x] = Integer.parseInt(split[x]);
//                if(arr[y][x] == 1) {
//                    breakablePoints.add(new Point(x, y));
//                }
            }
        }


        Point[] directions = new Point[]{
                new Point(-1, 0),
                new Point(1, 0),
                new Point(0, -1),
                new Point(0, 1),
        };

        boolean[][] visited = new boolean[M][N];

        points.add(new Point(0, 0));

        while (!points.isEmpty()) {
            Point p = points.poll();

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);
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
    }
}
