package kr.goldenmine.gold.gold4.p14502F;

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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int depth) {
            this(x, y);
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] arr = new int[M][N];

        Queue<Point> points = new LinkedList<>();

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
                if (arr[y][x] == 2) { // 초기 queue 엘리먼트 지정
                    points.add(new Point(x, y));
                }
            }
        }

        Point[] directions = new Point[]{
                new Point(-1, 0, -1),
                new Point(1, 0, -1),
                new Point(0, -1, -1),
                new Point(0, 1, -1),
        };

        boolean[][] visited = new boolean[M][N];

        int max = 0;

        int previousCount = points.size();

        // 바이러스가 퍼질 타일의 수가 3개가 아닌 숫자가 남았을 때 퍼뜨리고, 남은 0의 갯수를 세면 됨
        while (!points.isEmpty()) {
            for (int y = 0; y < M; y++) {
                for (int x = 0; x < N; x++) {
                    System.out.print(arr[y][x] + " ");
                }

                System.out.println();
            }
//            System.out.println();
            System.out.println(points.size() + ", " + points.peek());
//            if(nextCount != 3) {
//            int size = points.size();

            int count = 0;
            for (int c = 0; c < previousCount; c++) {
                Point p = points.poll();

                arr[p.y][p.x] = 2;

                for (int i = 0; i < 4; i++) {
                    Point next = p.add(directions[i]);
                    if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < M) {
                        if (arr[next.y][next.x] == 0) {
                            if (!visited[next.y][next.x]) {
                                points.add(next);
                                visited[next.y][next.x] = true;
                                count++;
                            }
                        }
                    }
                }
            }
            previousCount = count;
            if (points.size() == 3) {
                while (!points.isEmpty()) {
                    Point p = points.poll();

                    // 벽 세우기
                    arr[p.y][p.x] = 1;
                }
            }
//            } else {
//                while(!points.isEmpty()) {
//                    Point p = points.poll();
//
//                    // 벽 세우기
////                    arr[p.y][p.x] = 1;
//                }
//            }
        }

        int count = 0;

        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (arr[y][x] == 0) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
