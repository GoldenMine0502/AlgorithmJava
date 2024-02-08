package kr.goldenmine.platinum.platinum5.p1981A;

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

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static int bfs(int N, int[][] arr, int min, int max) {
        Queue<Point> queue = new LinkedList<>();

        if (min <= arr[0][0] && arr[0][0] <= max) {
            queue.add(new Point(0, 0, 0));
        }


        Point[] directions = new Point[]{
                new Point(1, 0, 0),
                new Point(-1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, -1, 0),
        };

        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.x == N - 1 && p.y == N - 1) {
                return p.depth;
            }

            for (int i = 0; i < directions.length; i++) {
                Point direction = directions[i];
                Point next = new Point(p.x + direction.x, p.y + direction.y, p.depth + 1);

                if (next.x < 0 || next.y < 0 || next.x >= N || next.y >= N) continue;

                // 범위밖이면 쳐냄
                int nextValue = arr[next.y][next.x];
                if (nextValue < min || nextValue > max) continue;

                if (!visited[next.y][next.x]) {
                    visited[next.y][next.x] = true;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    public static int binarySearch(int N, int[][] arr) {
        int left = 0;
        int right = 200;

        int res = 99999;

        /*
3
50 51 50
51 51 50
50 50 50

3
50 53 50
49 53 50
51 50 50

3
0 200 0
200 0 0
0 0 0

3
0 100 200
200 200 0
0 0 0
         */
        while (left <= right) {
            int mid = (left + right) / 2;

            boolean succeed = false;
            for (int i = 0; i <= 200; i++) {
                int check = bfs(N, arr, i, i + mid);
                if (check != -1) {
                    succeed = true;
                    break;
                }
            }

            if (succeed) {
                if (res > mid) {
                    res = mid;
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] arr = new int[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        int min = binarySearch(N, arr);


        System.out.println(min);
    }
}
