package kr.goldenmine.baekjoon.gold.gold2.p17244A;

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
        int point;

        public Point(int x, int y, int depth, int point) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.point = point;
        }

        public Point add(Point p, int depth, int point) {
            return new Point(x + p.x, y + p.y, depth, point);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", point=" + point +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0, 0, 0),
            new Point(1, 0, 0, 0),
            new Point(0, -1, 0, 0),
            new Point(0, 1, 0, 0),
    };
/*
7 6
#######
#SX..X#
#..####
#..X..#
#...X.#
#####E#
7 6
#######
#SX..X#
#..####
#..X..#
#X.#X.#
#####E#
3 3
###
#S#
#E#
 */


    public static int bfs(int X, int Y, int[][] graph, boolean[][][] visited, Point start, int count) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        visited[start.y][start.x][0] = true; // one visited

        // 2 << index = 비트마스크 값

        int min = Integer.MAX_VALUE;

        int finalBit = 0;
        for(int i = 0; i < count; i++) {
            finalBit |= (1 << i);
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();
//            System.out.println(current);

            if (graph[current.y][current.x] == 'E' && current.point == finalBit) {
               return current.depth;
            }

            for (int i = 0; i < directions.length; i++) {
                Point direction = directions[i];
                int nextPoint = current.point;
                Point next = current.add(direction, current.depth + 1, current.point); // 같은 깊이에서

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if(0 <= graph[next.y][next.x] && graph[next.y][next.x] <= 5) {
                        nextPoint |= (1 << graph[next.y][next.x]);
                        next.point = nextPoint;
                    }
                    if (graph[next.y][next.x] != '#') {
                        if (!visited[next.y][next.x][nextPoint]) {
                            visited[next.y][next.x][nextPoint] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        int X = scan.nextInt();
        int Y = scan.nextInt();

        boolean[][][] visited = new boolean[Y][X][32];
        int[][] graph = new int[Y][X];

        int index = 0;
        Point start = null;
        for (int y = 0; y < Y; y++) {
            String text = scan.next();

            for (int x = 0; x < X; x++) {
                graph[y][x] = text.charAt(x);
                if (graph[y][x] == 'S') {
                    start = new Point(x, y, 0, 0);
                }
                if (graph[y][x] == 'X') {
                    graph[y][x] = index++;
                }
            }
        }

        // public static int bfs(int X, int Y, int[][] graph, boolean[][][] visited, Point start, int count) {
        int result = bfs(X, Y, graph, visited, start, index);

        System.out.println(result);
    }
}
