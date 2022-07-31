package kr.goldenmine.gold.gold3.p2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point p) {
            return new Point(this.x + p.x, this.y + p.y);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    public static void bfs(int[][] arr, int X, int Y, boolean[][] visited, Point start, Predicate<Point> isQueueAdd, Consumer<Point> onPolled) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            onPolled.accept(p);

            if (arr[p.y][p.x] != 1) {
                for (int i = 0; i < 4; i++) {
                    Point next = p.add(directions[i]);

                    if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                        if (isQueueAdd.test(next)) {
                            if (!visited[next.y][next.x]) {
                                visited[next.y][next.x] = true;
                                points.add(next);
                            }
                        }
                    }
                }
            }
        }
    }

    static class Counter {
        int count;
        final int loop;

        public Counter(int loop) {
            this.loop = loop;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int arr[][] = new int[Y][X];

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        int lastCount = 0;
        int loop = 0;
        while (true) {
            Counter counter = new Counter(loop);

            boolean[][] visited = new boolean[Y][X];

            List<Point> points = new ArrayList<>();

            bfs(arr, X, Y, visited, new Point(0, 0), it -> true, it -> {
                if (arr[it.y][it.x] == 1) {
                    points.add(it);
                }
            });

            for (int pointIndex = 0; pointIndex < points.size(); pointIndex++) {
                Point it = points.get(pointIndex);
                int nearby = 0;
                for (int i = 0; i < 4; i++) {
                    Point next = it.add(directions[i]);

                    if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                        if (arr[next.y][next.x] != 1 && arr[next.y][next.x] != counter.loop + 2 && visited[next.y][next.x]) {
                            nearby++;
                        }
                    }
                }

                if (nearby >= 2) {
                    arr[it.y][it.x] = counter.loop + 2;
                    counter.count++;
                }
            }

            if (counter.count == 0) break;

            loop++;
            lastCount = counter.count;

//            for (int i = 0; i < Y; i++) {
//                for (int j = 0; j < X; j++) {
//                    System.out.print(arr[i][j] + " ");
//                }
//
//                System.out.println();
//            }
//
//            Thread.sleep(10000L);
        }

        System.out.println(loop);
//        System.out.println(lastCount);
    }
}
