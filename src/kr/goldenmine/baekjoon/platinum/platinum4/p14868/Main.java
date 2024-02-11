package kr.goldenmine.baekjoon.platinum.platinum4.p14868;

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
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static class Subset {
        int parent;
        int rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    static int T = 0;

    public static boolean bfs(Queue<Point> points, Subset[] parents, int[][] visited, int[][] arr, int N, int K) {
        T++;
        int size = points.size();

        for(int s = 0; s < size; s++) {
            Point p = points.poll();

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i], p.depth + 1);

                if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
                    if (visited[next.y][next.x] == -1) {
                        visited[next.y][next.x] = visited[p.y][p.x];
                        points.add(next);

                        arr[next.y][next.x] = arr[p.y][p.x];

                        for(int j = 0; j < 4; j++) {
                            Point nextnext = next.add(directions[i], next.depth);

                            if (nextnext.x >= 0 && nextnext.x < N && nextnext.y >= 0 && nextnext.y < N) {
                                if(visited[nextnext.y][nextnext.x] != -1 && visited[nextnext.y][nextnext.x] != visited[next.y][next.x]) {
                                    merge(parents, visited[next.y][next.x], visited[nextnext.y][nextnext.x]);

                                    if(merged == K - 1) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        int[][] arr = new int[N][N];
        Subset[] parents = new Subset[K + 1];
        int[][] visited = new int[N][N];

        for(int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                visited[y][x] = -1;
            }
        }

        Queue<Point> points = new LinkedList<>();

        for (int i = 1; i <= K; i++) {
            int x = scan.nextInt() - 1;
            int y = scan.nextInt() - 1;

            arr[y][x] = i;

            points.add(new Point(x, y));
            visited[y][x] = i;
            parents[i] = new Subset(i, 0);
        }

        while (merged != K - 1 && bfs(points, parents, visited, arr, N, K)) {

        }
        System.out.println(T);
    }

    static int merged = 0;

    public static int find(Subset[] arr, int value) {
        if (arr[value].parent == value)
            return value;
        return arr[value].parent = find(arr, arr[value].parent);
    }

    public static void merge(Subset[] arr, int x, int y) {
//        x = find(arr, x);
//        y = find(arr, y);
//        if (x == y) return;
//        arr[y] = x;
        int xroot = find(arr, x);
        int yroot = find(arr, y);

        if(xroot != yroot) merged++;

        if (arr[xroot].rank < arr[yroot].rank)
            arr[xroot].parent = yroot;
        else if (arr[yroot].rank < arr[xroot].rank)
            arr[yroot].parent = xroot;
        else {
            arr[xroot].parent = yroot;
            arr[yroot].rank++;
        }
    }

    public static boolean isUnion(Subset[] arr, int x, int y) {
        x = find(arr, x);
        y = find(arr, y);

        return x == y;
    }
}