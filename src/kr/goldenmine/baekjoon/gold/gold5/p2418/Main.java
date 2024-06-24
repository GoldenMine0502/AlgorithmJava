package kr.goldenmine.baekjoon.gold.gold5.p2418;

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
        int depth;

        Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
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

    static Point[] directions = {
            new Point(1, 0, 0),
            new Point(-1, 0, 0),
            new Point(0, 1, 0),
            new Point(0, -1, 0),

            new Point(1, 1, 0),
            new Point(-1, 1, 0),
            new Point(1, -1, 0),
            new Point(-1, -1, 0),
    };

    public static int bfs(Point start, int X, int Y, int[][] map, boolean[][] visited, String find) {
        Queue<Point> queue = new LinkedList<>();

        if(map[start.y][start.x] == find.charAt(0))
            queue.add(start);

        int count = 0;
        while(!queue.isEmpty()) {
            Point p = queue.poll();

//            System.out.println(p.depth);
            if(p.depth == find.length()) {
                count++;
                continue;
            }

            for(Point direction : directions) {
                Point next = new Point(p.x + direction.x, p.y + direction.y, p.depth + 1);
                if(next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
//                    System.out.println(next + ", " + (map[next.y][next.x] == find.charAt(next.depth)));

                    if(next.depth == find.length() || map[next.y][next.x] == find.charAt(next.depth)) {
                        if(!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int H = scan.nextInt();
        int W = scan.nextInt();
        int L = scan.nextInt();

        int[][] arr = new int[H][W];
        for(int i = 0; i < H; i++) {
            String line = scan.next();
            for(int j = 0; j < W; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        int total = 0;
        String text = scan.next();

        for(int y = 0; y < H; y++) {
            for(int x = 0; x < W; x++) {
//        int x = 3;
//        int y = 0;
                Point start = new Point(x, y, 0);
                total += bfs(start, W, H, arr, new boolean[H][W], text);
            }
        }

        System.out.println(total);
    }
}
