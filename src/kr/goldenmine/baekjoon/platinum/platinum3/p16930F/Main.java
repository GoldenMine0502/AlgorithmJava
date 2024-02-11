package kr.goldenmine.baekjoon.platinum.platinum3.p16930F;

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
        int lastDirection;
        int times;
        int tempK;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int lastDirection, int times, int tempK) {
            this(x, y);
            this.lastDirection = lastDirection;
            this.times = times;
            this.tempK = tempK;
        }

        public Point add(Point p, int lastDirection, int times, int tempK) {
            return new Point(x + p.x, y + p.y, lastDirection, times, tempK);
        }

        public Point mul(int values) {
            return new Point(x * values, y * values);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", lastDirection=" + lastDirection +
                    ", times=" + times +
                    ", tempK=" + tempK +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static Queue<Point> points = new LinkedList<>();

    public static int bfs(int X, int Y, char[][] arr, int[][] visited, Point start, Point finish, int K) {
        visited[start.y][start.x] = 1;

        int min = -1;

        while (!points.isEmpty()) {
            Point p = points.poll();

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= K; j++) {
                    Point next = p.add(directions[i].mul(j), i, p.times + 1, j);

                    if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                        if (arr[next.y][next.x] == '.') {
                            if (p.x == finish.x && p.y == finish.y) {
                                if (min == -1) {
                                    min = p.times;
                                } else {
                                    min = Math.min(p.times, min);
                                }
                            } else {
                                if (visited[next.y][next.x] == 0) {
                                    visited[next.y][next.x] = visited[p.y][p.x] + 1;
                                    points.add(next);
                                } else if(visited[next.y][next.x] <= visited[p.y][p.x]){
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();
        int K = scan.nextInt();

        char[][] arr = new char[Y][X];
        for (int y = 0; y < Y; y++) {
            String line = scan.next();
            for (int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x);
            }
        }

        int startY = scan.nextInt() - 1;
        int startX = scan.nextInt() - 1;
        int finishY = scan.nextInt() - 1;
        int finishX = scan.nextInt() - 1;

        Point start = new Point(startX, startY);
        Point finish = new Point(finishX, finishY);

//        System.out.println(start);
//        System.out.println(finish);

        int[][] visited = new int[Y][X];

//        int times = 0;
        points.add(start);
        while (true) {
            int result = bfs(X, Y, arr, visited, start, finish, K);
//            System.out.println(result);
//            times++;

            if (result != -1) {
                System.out.println(result);
                return;
            }

            if (points.isEmpty()) {
                break;
            }
        }
        System.out.println(-1);
    }
}
