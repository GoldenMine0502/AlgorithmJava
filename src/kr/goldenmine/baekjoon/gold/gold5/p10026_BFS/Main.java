package kr.goldenmine.baekjoon.gold.gold5.p10026_BFS;

import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
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
            return new Point(x + p.x, y + p.y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point) {
                Point p = (Point) obj;
                return x == p.x && y == p.y;
            }

            return false;
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    public static void bfs(int X, int Y, boolean[][] visited, Point start, Predicate<Point> isQueueAdd, Consumer<Point> onPolled) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while(!points.isEmpty()) {
            Point p = points.poll();

            onPolled.accept(p);

            for(int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (isQueueAdd.test(next)) {
                        if(!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static int bfs(Character[][] arr, int N) {
        boolean[][] visited = new boolean[N][N];

        int count = 0;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(!visited[y][x]) {
                    count++;
                    char value = arr[y][x];
                    visited[y][x] = true;

                    bfs(N, N, visited, new Point(x, y), it -> arr[it.y][it.x] == value, it -> {});
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        Character[][] normalArr = new Character[N][N];
        Character[][] abnormalArr = new Character[N][N];

        for(int i = 0; i < N; i++) {
            String line = scan.nextLine();
            for(int j = 0; j < N; j++) {
                normalArr[i][j] = line.charAt(j);

                // 색약 환자는 무조건 R,B만 확인
                abnormalArr[i][j] = normalArr[i][j];
                if(normalArr[i][j] == 'G') {
                    abnormalArr[i][j] = 'R';
                }
            }
        }

        System.out.println(bfs(normalArr, N) + " " + bfs(abnormalArr, N));
    }
}
