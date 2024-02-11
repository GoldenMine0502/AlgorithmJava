package kr.goldenmine.baekjoon.gold.gold3.p16236;

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

    /*
    3 3 9
1 1 1000 1 1
1 2 999 2 2
2 1 1000 3 3
2 2 999 4 4
1 3 1000 1 5
3 1 999 2 6
2 3 1000 3 7
3 2 999 4 8
3 3 1000 1 9
     */


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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(0, 1),
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
    };

    public static Point getFishNearby(int X, int Y, int[][] arr, boolean[][] visited, Point start, int size) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        Point best = null;
        List<Point> results = new ArrayList<>();

        while (!points.isEmpty()) {
            Point p = points.poll();

            if (arr[p.y][p.x] != 0 && arr[p.y][p.x] < size) {
                results.add(p);
            }

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i], p.depth + 1);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (!visited[next.y][next.x]) {
                        visited[next.y][next.x] = true;
                        points.add(next);
                    }
                }
            }
        }

        results.sort((o1, o2) -> {
            if (o1.depth != o2.depth) {
                return Integer.compare(o1.depth, o2.depth);
            } else {
                if (o1.y != o2.y) {
                    return Integer.compare(o1.y, o2.y);
                } else {
                    return Integer.compare(o1.x, o2.x);
                }
            }
        });

        if (results.size() > 0) {
            best = results.get(0);
        }

        // 먹을 물고기 리턴
        return best;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] arr = new int[N][N];
        Point previous = null;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();

                if (arr[y][x] == 9) {
                    previous = new Point(x, y);
                }
            }
        }

        int size = 2;
        int eaten = 0;
        int time = 0;

        while (true) {
            boolean[][] visited = new boolean[N][N];
            arr[previous.y][previous.x] = 0;
            previous = getFishNearby(N, N, arr, visited, previous, size);
//            System.out.println(previous);

            if (previous != null) {
                eaten++;
                if (eaten == size) {
                    eaten = 0;
                    size++;
                }
                arr[previous.y][previous.x] = 9;

                time += previous.depth;
//                System.out.println(time + ", " + size + ", " + eaten);
            } else {
                break;
            }

//            for (int y = 0; y < N; y++) {
//                for (int x = 0; x < N; x++) {
//                    System.out.print(arr[y][x] + " ");
//                }
//                System.out.println();
//            }

            previous.depth = 0;
        }

        System.out.println(time);
    }
}
