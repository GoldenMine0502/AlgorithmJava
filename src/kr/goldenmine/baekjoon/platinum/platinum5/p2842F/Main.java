package kr.goldenmine.baekjoon.platinum.platinum5.p2842F;

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
        int minus = 9999999;
        int plus = -9999999;

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
                    ", minimum=" + minus +
                    ", maximum=" + plus +
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

    static class Difference {
        int minus;
        int plus;

        public Difference(int minus, int plus) {
            this.minus = minus;
            this.plus = plus;
        }

        @Override
        public String toString() {
            return "Difference{" +
                    "minus=" + minus +
                    ", plus=" + plus +
                    '}';
        }
    }

    public static final int MAX_VALUE = 9999999;
    public static final int MIN_VALUE = -9999999;

    public static void bfs(int X, int Y, int[][] arr, Difference[][][] differences, Point start) {
        Queue<Point> points = new LinkedList<>();

        start.plus = 0;
        start.minus = 0;
        points.add(start);

        for(int i = 0; i < 8; i++)
            differences[start.y][start.x][i] = new Difference(0, 0);

        while (!points.isEmpty()) {
            Point p = points.poll();
            System.out.println(p);

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);
                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    next.plus = Math.max(next.plus, arr[next.y][next.x] - arr[start.y][start.x]);
                    next.minus = Math.min(next.minus, arr[next.y][next.x] - arr[start.y][start.x]);

//                    System.out.println(p + ", " + next + ", " + arr[next.y][next.x] + ", " + difference + ", " + minimumDifferences[next.y][next.x]);

                    if (differences[next.y][next.x] == null || (next.plus < differences[next.y][next.x][i].plus || next.minus > differences[next.y][next.x][i].minus)) {
//                        int min = Math.max(differences[next.y][next.x] != null ? differences[next.y][next.x].minus : MIN_VALUE, next.minus);
//                        int max = Math.min(differences[next.y][next.x] != null ? differences[next.y][next.x].plus : MAX_VALUE, next.plus);

//                        differences[next.y][next.x] = new Difference(next.minus, next.plus);
                        points.add(next);
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

                if (arr[y][x] == 'P') {
                    startX = x;
                    startY = y;
                    Ks.add(new Point(startX, startY));
                }
                if (arr[y][x] == 'K') {
                    Ks.add(new Point(x, y));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                arr2[y][x] = scan.nextInt();
            }
        }

//        System.out.println(startX + ", " + startY);

        // visited에는 P에서 해당 값까지 필요한 높이 절댓값을 저장한다.
        Difference[][] visited = new Difference[N][N];

        Point start = new Point(startX, startY);

//        bfs(N, N, arr2, visited, start);

//        for(int y = 0; y < N; y++) {
//            for(int x = 0; x < N; x++) {
//                System.out.print(arr2[y][x] + " ");
//            }
//            System.out.println();
//        }
//
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(visited[y][x] + " ");
            }
            System.out.println();
        }

        int min = MAX_VALUE;
        int max = MIN_VALUE;

        for (int i = 0; i < Ks.size(); i++) {
            Point k = Ks.get(i);
//            int difference = arr2[k.y][k.x] - arr2[startY][startX];
//            System.out.println(k + ", " + difference);

            max = Math.max(max, visited[k.y][k.x].plus);
            min = Math.min(min, visited[k.y][k.x].minus);
        }

        /*
4
P..K
....
....
K..K
3 3 15 9
9 2 15 3
8 3 12 4
3 9 15 5
         */

//        System.out.println(min + ", " + max);

        System.out.println(max - min);
    }
}
