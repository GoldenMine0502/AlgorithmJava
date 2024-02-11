package kr.goldenmine.baekjoon.gold.gold2.p2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int d;
        int previousDx;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int d) {
            this(x, y);
            this.d = d;
        }

        public Point add(Point p, int d) {
            Point p2 = new Point(x + p.x, y + p.y, d);
            p2.previousDx = p.previousDx;
            return p2;
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
    };

//    public static int bfs(int X, int Y, boolean[][] visited, Point start) {
//        Queue<Point> points = new LinkedList<>();
//
//        points.add(start);
//        visited[start.y][start.x] = true;
//
//        int max = 0;
//
//        while (!points.isEmpty()) {
//            Point p = points.poll();
//
//            max = Math.max(max, p.d);
//
//            for (int i = 0; i < 4; i++) {
//                // y인덱스가 변하는 경우에만 depth 증가.
//                // 단, depth 증가 이후 x가 변하지 않았다면 + 1 하면 안됨
//                Point next = p.add(directions[i], directions[i].y != 0 && p.previousDx != p.x ? p.d + 1 : p.d);
//                next.previousDx = p.x;
//
//                //
//                if(next.y > Y) next.y = 1;
//                if(next.y < 1) next.y = Y;
//
//                if (next.x >= 1 && next.x <= X && next.y >= 1 && next.y <= Y) {
//                    if (isQueueAdd.test(next)) {
//                        if (!visited[next.y][next.x]) {
//                            visited[next.y][next.x] = true;
//                            points.add(next);
//                        }
//                    }
//                }
//            }
//        }
//
//        return max;
//    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int L = scan.nextInt();
        int[][] arr = new int[L + 1][N + 1];

        for (int l = 1; l <= L; l++) {
            for (int n = 0; n < N + 1; n++) {
                int value = scan.nextInt();
                if(value >= 0) {
                    arr[l][N] = 1;
                } else {
                    break;
                }
            }
        }

//        bfs(N, L)
        // L의 변화 갯수를 세면 된다.
    }
}
