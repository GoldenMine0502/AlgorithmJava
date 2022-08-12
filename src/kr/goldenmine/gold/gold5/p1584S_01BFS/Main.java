package kr.goldenmine.gold.gold5.p1584S_01BFS;

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
        int hp;

        public Point(int x, int y, int hp) {
            this.x = x;
            this.y = y;
            this.hp = hp;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y, 0);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", hp=" + hp +
                    '}';
        }
    }

    static Point[] directions = {
            new Point(0, 1, 0),
            new Point(0, -1, 0),
            new Point(1, 0, 0),
            new Point(-1, 0, 0),
    };

    static Queue<Point> queue = new LinkedList<>();

    public static int bfs(int[][] arr, int X, int Y, boolean[][] visited) {
        Queue<Point> points = new LinkedList<>();

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if(p.x == X - 1 && p.y == Y - 1) {
                return p.hp;
            }

//            System.out.println(p);

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (!visited[next.y][next.x]) {

                        visited[next.y][next.x] = true;
                        if (arr[next.y][next.x] == 1) { // 다음 큐에 대기
                            next.hp = p.hp + 1;
                            points.add(next);
                        } else if(arr[next.y][next.x] == 0){ // 계속 탐색
                            next.hp = p.hp;
                            queue.add(next);
                        }
                    }
                }
            }
        }

        queue = points;

        return -1;
    }

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        // inputs
        int N = scan.nextInt();

        // 위험 = 1, 죽음 = 2
        int[][] arr = new int[501][501];

        for (int i = 0; i < N; i++) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();

            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    arr[y][x] = 1;
                }
            }
        }

        int M = scan.nextInt();

        for (int i = 0; i < M; i++) {
            int x1 = scan.nextInt();
            int y1 = scan.nextInt();
            int x2 = scan.nextInt();
            int y2 = scan.nextInt();

            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    arr[y][x] = 2;
                }
            }
        }

        // solve
        queue.add(new Point(0, 0, 0));

        boolean[][] visited = new boolean[501][501];

        while(true) {
            int hp = bfs(arr, 501, 501, visited);
            if(hp != -1) {
                System.out.println(hp);
                return;
            }
            if(queue.isEmpty()) break;
        }

        System.out.println(-1);
    }
}
