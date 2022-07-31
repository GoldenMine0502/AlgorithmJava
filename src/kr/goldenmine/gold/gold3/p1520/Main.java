package kr.goldenmine.gold.gold3.p1520;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int M = scan.nextInt();
        int N = scan.nextInt();

        int[][] arr = new int[M][N];

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        int[][] cache = new int[M][N];

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {

            }
        }

//        Queue<Point> points = new LinkedList<>();
//        points.add(new Point(0, 0));
//
//        do {
//            Point p = points.poll();
//            int value = arr[p.y][p.x];
//
//            cache[p.y][p.x]++;
//
//            if(p.y == M - 1 && p.x == N - 1) continue;
//
//            if(p.x > 0 && arr[p.y][p.x - 1] < value) {
//                Point left = p.add(-1, 0);
//
//                points.add(left);
//            }
//
//            if(p.x < N - 1 && arr[p.y][p.x + 1] < value) {
//                Point left = p.add(1, 0);
//
//                points.add(left);
//            }
//
//            if(p.y > 0 && arr[p.y - 1][p.x] < value) {
//                Point left = p.add(0, -1);
//
//                points.add(left);
//            }
//
//            if(p.y < M - 1 && arr[p.y + 1][p.x] < value) {
//                Point left = p.add(0, 1);
//
//                points.add(left);
//            }
//        } while(!points.isEmpty());

//        for(int i = 0; i < M; i++) {
//            for(int j = 0; j < N; j++) {
//                System.out.print(cache[i][j] + " ");
//            }
//
//            System.out.println();
//        }

        System.out.println(cache[M - 1][N - 1]);
    }
}
