package kr.goldenmine.gold.gold4.p1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int depth;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static int max = 0;

    public static void dfs(int X, int Y, int[][] arr, boolean[] visited, Point point, int count) {
//        if (visited[point.y][point.x] >= point.visitedAlphabets.size()) {
//            return;
//        }

//        visited[point.y][point.x] = Math.max(visited[point.y][point.x], point.visitedAlphabets.size());

//        System.out.println(point);

        if(visited[arr[point.y][point.x]]) {
            max = Math.max(max, count);
            return;
        }

        visited[arr[point.y][point.x]] = true;

        for (int i = 0; i < 4; i++) {
            Point next = point.add(directions[i]);

            if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                dfs(X, Y, arr, visited, next, count + 1);
            }
        }

        visited[arr[point.y][point.x]] = false;
    }

//    public static int bfs(int X, int Y, int[][] arr, boolean[][] visited, Point start) {
//        Queue<Point> points = new LinkedList<>();
//
//        points.add(start);
//        visited[start.y][start.x] = true;
//
//        int max = 0;
//
//        while (!points.isEmpty()) {
//            Point p = points.poll();
//
//            max = Math.max(max, p.depth);
//
//            System.out.println(p);
//
//            for (int i = 0; i < 4; i++) {
//                Point next = p.add(directions[i]);
//                next.depth = p.depth + 1;
//
//                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
//                    if(!p.visitedAlphabets.contains(arr[next.y][next.x])) {
//                        if (visited[next.y][next.x] < p.visitedAlphabets.size() + 1) {
//                            next.visitedAlphabets.addAll(p.visitedAlphabets);
//                            next.visitedAlphabets.add(arr[next.y][next.x]);
//                            visited[next.y][next.x] = p.visitedAlphabets.size() + 1;
//                            points.add(next);
//                        }
//                    }
//                }
//            }
//        }
//
//        return max;
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] arr = new int[Y][X];

        for (int y = 0; y < Y; y++) {
            String line = scan.next();

            for (int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x) - 'A';
            }
        }

        boolean[] visited = new boolean[26];

        Point start = new Point(0, 0);

        dfs(X, Y, arr, visited, start, 0);
//
//        int max = 0;
//
//        for (int y = 0; y < Y; y++) {
//            for (int x = 0; x < X; x++) {
////                System.out.print(visited[y][x] + " ");
//                max = Math.max(max, visited[y][x]);
//            }
////            System.out.println();
//        }
        System.out.println(max);
//        System.out.println(max);
    }
}
