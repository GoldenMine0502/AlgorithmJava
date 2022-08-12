package kr.goldenmine.platinum.platinum3.p16930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

    public static int bfs(int X, int Y, char[][] arr, boolean[][] visited, Point start, Point finish, int K) {
        Queue<Point> nexts = new LinkedList<>();

        visited[start.y][start.x] = true;

        int min = -1;

        while (!points.isEmpty()) {
            Point p = points.poll();

            visited[p.y][p.x] = true;

            System.out.println(p);

            if(p.x == finish.x && p.y == finish.y) {
                if(min == -1) {
                    min = p.times;
                } else {
                    min = Math.min(p.times, min);
                }
            }

            for (int i = 0; i < 4; i++) {
                boolean isSameDirection = p.lastDirection == i && p.tempK < K;
                int tempK = isSameDirection ? p.tempK + 1 : 1;
                int times = isSameDirection ? p.times : p.times + 1;

                Point next = p.add(directions[i], i, times, tempK);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
//                    if (isQueueAdd.test(next)) {
                    if(arr[next.y][next.x] == '.') {
                        if (!visited[next.y][next.x] || isSameDirection) {
                            if(isSameDirection) {
                                visited[next.y][next.x] = true;
                                points.add(next);
                            } else {
                                if(!visited[next.y][next.x])
                                    nexts.add(next);
                            }
                        }
                    }
//                    }
                }
            }
        }

        points = nexts;

        return min;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();
        int K = scan.nextInt();

        char[][] arr = new char[Y][X];
        for(int y = 0; y < Y; y++) {
            String line = scan.next();
            for(int x = 0; x < X; x++) {
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

        boolean[][] visited = new boolean[Y][X];

//        int times = 0;
        points.add(start);
        while(true) {
            int result = bfs(X, Y, arr, visited, start, finish, K);
//            System.out.println(result);
//            times++;

            if(result != -1) {
                System.out.println(result);
                return;
            }

            if(points.isEmpty()) {
                break;
            }
        }
        System.out.println(-1);
    }
}
