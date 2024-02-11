package kr.goldenmine.baekjoon.unknown.p2667;

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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y);
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

        int size = 0;

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            size++;

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

        return size;
    }

    public static void bfs(int[][] arr, int X, int Y) {
        boolean[][] visited = new boolean[Y][X];

        List<Integer> list = new ArrayList<>();

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (!visited[y][x] && arr[y][x] == 1) {
                    visited[y][x] = true;

                    int size = bfs(X, Y, arr, visited, new Point(x, y));

                    list.add(size);
                }
            }
        }
        list.sort(Integer::compareTo);

        System.out.println(list.size());

        for (int i : list) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();

        int[][] arr = new int[N][N];

        for (int y = 0; y < N; y++) {
            String line = scan.next();

            for (int x = 0; x < N; x++) {
                arr[y][x] = line.charAt(x) - '0';
            }
        }
        bfs(arr, N, N);
    }
}
