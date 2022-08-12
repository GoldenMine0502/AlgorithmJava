package kr.goldenmine.platinum.platinum5.p2842;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

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
        int minimum = 9999999;
        int maximum = -9999999;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int minimum, int maximum) {
            this(x, y);
            this.minimum = minimum;
            this.maximum = maximum;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, minimum, maximum);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", minimum=" + minimum +
                    ", maximum=" + maximum +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, -1),
            new Point(-1, 0),
            new Point(-1, 1),
            new Point(0, -1),
            new Point(0, 1),
            new Point(1, -1),
            new Point(1, 0),
            new Point(1, 1),
    };

    public static final int MAX_VALUE = 9999999;
    public static final int MIN_VALUE = -1;

    public static void bfs(int X, int Y, int[][] arr, int[][] minimumDifferences, Point start) {
        Queue<Point> points = new LinkedList<>();

        start.maximum = arr[start.y][start.x];
        start.minimum = arr[start.y][start.x];
        points.add(start);

        minimumDifferences[start.y][start.x] = 0;

        while (!points.isEmpty()) {
            Point p = points.poll();
            System.out.println(p);

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);
                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    next.maximum = Math.max(next.maximum, arr[next.y][next.x]);
                    next.minimum = Math.min(next.minimum, arr[next.y][next.x]);

                    int difference = next.maximum - next.minimum;

                    System.out.println(p + ", " + next + ", " + arr[next.y][next.x] + ", " + difference + ", " + minimumDifferences[next.y][next.x]);

                    if (difference < minimumDifferences[next.y][next.x]) {
                        minimumDifferences[next.y][next.x] = difference;
                        points.add(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<Point> Ks = new ArrayList<>();

        int startX = -1;
        int startY = -1;

        char[][] arr = new char[N][N];
        int[][] arr2 = new int[N][N];

        for (int y = 0; y < N; y++) {
            String line = scan.next();
            for (int x = 0; x < N; x++) {
                arr[y][x] = line.charAt(x);

                if(arr[y][x] == 'P') {
                    startX = x;
                    startY = y;
                    Ks.add(new Point(startX, startY));
                }
                if(arr[y][x] == 'K') {
                    Ks.add(new Point(x, y));
                }
            }
        }

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                arr2[y][x] = scan.nextInt();
            }
        }

//        System.out.println(startX + ", " + startY);

        // visited에는 P에서 해당 값까지 필요한 높이 절댓값을 저장한다.
        int[][] visited = new int[N][N];
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                visited[y][x] = MAX_VALUE;
            }
        }

        Point start = new Point(startX, startY);

        bfs(N, N, arr2, visited, start);

//        for(int y = 0; y < N; y++) {
//            for(int x = 0; x < N; x++) {
//                System.out.print(arr2[y][x] + " ");
//            }
//            System.out.println();
//        }
//
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                System.out.print(visited[y][x] + " ");
            }
            System.out.println();
        }

        int min = 0;
        int max = 0;

        for(int i = 0; i < Ks.size(); i++) {
            Point k = Ks.get(i);
            int difference = arr2[k.y][k.x] - arr2[startY][startX];
//            System.out.println(k + ", " + difference);

            if(difference >= 0) {
                max = Math.max(max, visited[k.y][k.x]);
            } else {
                min = Math.min(min, -visited[k.y][k.x]);
            }
        }

//        System.out.println(min + ", " + max);

        System.out.println(max - min);
    }
}
