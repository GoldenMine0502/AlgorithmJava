package kr.goldenmine.gold.gold3.p2146;

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
        int root;
        int depth;

        public Point(int x, int y, int root, int depth) {
            this.x = x;
            this.y = y;
            this.root = root;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", root=" + root +
                    ", depth=" + depth +
                    '}';
        }

        public Point add(Point p, int depth) {
            return new Point(x + p.x, y + p.y, root, depth);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0, 0, 0),
            new Point(1, 0, 0, 0),
            new Point(0, -1, 0, 0),
            new Point(0, 1, 0, 0),
    };

    public static void bfs(int N, int[][] arr, boolean[][] visited, int count, int x, int y) {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(x, y, 0, 0));

        while (!points.isEmpty()) {
            Point p = points.poll();

            arr[p.y][p.x] = count;

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i], 0);

                if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
                    if (arr[next.y][next.x] == 1) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int[][] arr = new int[N][N];

        Queue<Point> points = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        boolean[][] visited = new boolean[N][N];


        int count = 2;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x] && arr[y][x] == 1) {
                    visited[y][x] = true;
                    bfs(N, arr, visited, count, x, y);
                    count++;
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (arr[y][x] != 0) {
                    points.add(new Point(x, y, arr[y][x], 0));
                }
            }
        }
//
//
//        for(int y = 0; y < N; y++) {
//            for (int x = 0; x < N; x++) {
//                System.out.print((visited[y][x] ? 1 : 0) + " ");
//            }
//            System.out.println();
//        }

        int minDepth = 0;
        while (!points.isEmpty()) {
            Point p = points.poll();

//            System.out.println(p);

            arr[p.y][p.x] = -p.root;

//            if (minDepth == p.depth) {
//                minDepth++;
//                for (int y = 0; y < N; y++) {
//                    for (int x = 0; x < N; x++) {
//                        System.out.print(arr[y][x] + " ");
//                    }
//                    System.out.println();
//                }
//            }

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i], p.depth + 1);

                if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
//                    System.out.println("next: " + next + ", " + p.root + ", " + arr[next.y][next.x]);
                    if (arr[next.y][next.x] < 0) {
                        System.out.println(next.depth - arr[next.y][next.x]);
                        return;
                    }
                    if (arr[next.y][next.x] == 0) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }

//        for(int y = 0; y < N; y++) {
//            for (int x = 0; x < N; x++) {
//                System.out.print(visited[y][x] + " ");
//            }
//            System.out.println();
//        }
    }
}
