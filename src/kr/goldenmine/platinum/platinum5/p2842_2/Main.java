package kr.goldenmine.platinum.platinum5.p2842_2;

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
        int minus = 0;
        int plus = 0;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int minus, int plus) {
            this(x, y);
            this.minus = minus;
            this.plus = plus;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, minus, plus);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", minus=" + minus +
                    ", plus=" + plus +
                    '}';
        }
    }

    static class Difference {
        int minus;
        int plus;

        Point last;

        public Difference(int minus, int plus, Point last) {
            this.minus = minus;
            this.plus = plus;
            this.last = last;
        }

        @Override
        public String toString() {
            return "Difference{" +
                    "minus=" + minus +
                    ", plus=" + plus +
                    '}';
        }
    }

    /*
    netlify
    recoli
     */

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
    public static final int MIN_VALUE = -9999999;

    public static void bfs(int X, int Y, int[][] arr, Difference[][] differences, Point start) {
        Queue<Point> points = new LinkedList<>();

        start.plus = 0;
        start.minus = 0;
        points.add(start);

        while (!points.isEmpty()) {
            Point p = points.poll();
            System.out.println(p);

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);
                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    next.plus = Math.max(next.plus, arr[next.y][next.x] - arr[start.y][start.x]);
                    next.minus = Math.min(next.minus, arr[next.y][next.x] - arr[start.y][start.x]);

//                    System.out.println(p + ", " + next + ", " + arr[next.y][next.x] + ", " + difference + ", " + minimumDifferences[next.y][next.x]);

                    if (differences[next.y][next.x] == null) {
                        differences[next.y][next.x] = new Difference(next.minus, next.plus, p);
                        points.add(next);
                    } else {
                        int diff = differences[next.y][next.x].plus - differences[next.y][next.x].minus;
                        int nextDiff = next.plus - next.minus;

                        if(nextDiff < diff) {
                            differences[next.y][next.x] = new Difference(next.minus, next.plus, p);
                            points.add(next);
                        }
                    }
//                    else if (next.plus < differences[next.y][next.x].plus) {
//                        int max = Math.min(differences[next.y][next.x] != null ? differences[next.y][next.x].plus : MAX_VALUE, next.plus);
//
//                        differences[next.y][next.x] = new Difference(differences[next.y][next.x].minus, max);
//                        points.add(next);
//                    } else if (next.minus > differences[next.y][next.x].minus) {
//                        int min = Math.max(differences[next.y][next.x] != null ? differences[next.y][next.x].minus : MIN_VALUE, next.minus);
//
//                        differences[next.y][next.x] = new Difference(min, differences[next.y][next.x].plus);
//                        points.add(next);
//                    }
                }
            }
        }
    }

    public static void dfs(int X, int Y, int[][] arr, Difference[][] differences, Point start, Point point) {
        for(int i = 0; i < directions.length; i++) {
            Point next = point.add(directions[i]);

            if(next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                next.plus = Math.max(next.plus, arr[next.y][next.x] - arr[start.y][start.x]);
                next.minus = Math.min(next.minus, arr[next.y][next.x] - arr[start.y][start.x]);

                if (differences[next.y][next.x] == null) {
                    differences[next.y][next.x] = new Difference(next.minus, next.plus, point);
//                    System.out.println(next);
                    dfs(X, Y, arr, differences, start, next);
                } else {
                    int diff = differences[next.y][next.x].plus - differences[next.y][next.x].minus;
                    int currentDiff = next.plus - next.minus;

                    if(currentDiff < diff) {
                        differences[next.y][next.x] = new Difference(next.minus, next.plus, point);
                        dfs(X, Y, arr, differences, start, next);
                    }
                }
//                else if (next.plus < differences[next.y][next.x].plus) {
//                    differences[next.y][next.x] = new Difference(Math.max(differences[next.y][next.x].minus, next.minus), Math.min(differences[next.y][next.x].plus, next.plus));
////                    System.out.println(next);
//                    dfs(X, Y, arr, differences, start, next);
//                } else if (next.minus > differences[next.y][next.x].minus) {
//                    differences[next.y][next.x] = new Difference(Math.max(differences[next.y][next.x].minus, next.minus), Math.min(differences[next.y][next.x].plus, next.plus));
////                    System.out.println(next);
//                    dfs(X, Y, arr, differences, start, next);
//                }
            }
        }
    }

    // 이거 최단거리 역추적 문제임...
    public static void main(String[] args) {
                FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<Point> Ks = new ArrayList<>();

        int startX = -1;
        int startY = -1;

        char[][] types = new char[N][N];
        int[][] values = new int[N][N];

        for (int y = 0; y < N; y++) {
            String line = scan.next();
            for (int x = 0; x < N; x++) {
                types[y][x] = line.charAt(x);

                if (types[y][x] == 'P') {
                    startX = x;
                    startY = y;
//                    Ks.add(new Point(x, y));
                }
                if (types[y][x] == 'K') {
                    Ks.add(new Point(x, y));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                values[y][x] = scan.nextInt();
            }
        }

        Difference[][] diffs = new Difference[N][N];
        diffs[startY][startX] = new Difference(0, 0, new Point(startX, startY));

        bfs(N, N, values, diffs, new Point(startX, startY));

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(diffs[y][x] + " ");
            }
            System.out.println();
        }

        List<Point> visitedPoints = new ArrayList<>();

        for(int i = 0; i < Ks.size(); i++) {
            Point last = Ks.get(i);

            while(!(last.x == startX && last.y == startY)) {
                visitedPoints.add(last);
                last = diffs[last.y][last.x].last;
            }
        }

        System.out.println(visitedPoints);

        int min = 9999999;
        int max = -1;

        for (int i = 0; i < visitedPoints.size(); i++) {
            Point p = visitedPoints.get(i);
//            int difference = arr2[k.y][k.x] - arr2[startY][startX];
//            System.out.println(k + ", " + difference);

            max = Math.max(max, values[p.y][p.x]);
            min = Math.min(min, values[p.y][p.x]);
            System.out.println(values[p.y][p.x]);
        }

        System.out.println(max - min);
    }
    /*
    반례

4
P..K
....
....
K..K
3 2 15 4
7 4 15 2
2 3 3 1
3 4 15 6

4
P..K
....
....
K..K
3 2 15 2
7 4 15 2
2 3 5 1
3 4 15 6

4
P..K
....
....
K..K
3 2 15 2
7 4 15 2
2 3 12 1
3 4 15 6

4
P..K
....
....
K..K
3 2 15 2
7 15 15 2
2 15 10 1
3 4 15 6
....
....
....
....

     */
}
