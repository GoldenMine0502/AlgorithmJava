package kr.goldenmine.baekjoon.gold.gold4.p2636S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

        public Point add(int x, int y) {
            return new Point(this.x + x, this.y + y);
        }
    }

    public static int meltAndCount(int[][] arr, int loop, int X, int Y, boolean[][] visited) {
        int count = 0;

        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.addLast(new Point(0, 0)); // 0,0부터 시작
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Point point = queue.removeFirst();

            int value = arr[point.y][point.x];

            // 1 = 안녹은 치즈 있음
            // 0 = 애초에 없었음
            // 다른 수 = 예전에 녹은 적 있음
            if(value != 1) {
                // top
                if (point.x > 0 && !visited[point.y][point.x - 1]) {
                    queue.add(point.add(-1, 0));
                    visited[point.y][point.x - 1] = true;
                }

                // bottom
                if (point.x < X - 1 && !visited[point.y][point.x + 1]) {
                    queue.add(point.add(1, 0));
                    visited[point.y][point.x + 1] = true;
                }

                // left
                if (point.y > 0 && !visited[point.y - 1][point.x]) {
                    queue.add(point.add(0, -1));
                    visited[point.y - 1][point.x] = true;
                }

                // right
                if (point.y < Y - 1 && !visited[point.y + 1][point.x]) {
                    queue.add(point.add(0, 1));
                    visited[point.y + 1][point.x] = true;
                }
            } else {
                count++;

                arr[point.y][point.x] = loop + 2;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int arr[][] = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        int lastCount = 0;
        int loop = 0;
        while(true) {
            boolean[][] visited = new boolean[N][M];
            int count = meltAndCount(arr, loop, M, N, visited);

            if(count == 0) break;

            loop++;
            lastCount = count;
        }

        System.out.println(loop);
        System.out.println(lastCount);
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//
//            System.out.println();
//        }
    }
}
