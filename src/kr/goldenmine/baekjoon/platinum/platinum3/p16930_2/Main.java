package kr.goldenmine.baekjoon.platinum.platinum3.p16930_2;

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
        int lastDirection;
        int times;
        int tempK;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int lastDirection, int times, int tempK) {
            this(x, y);
            this.lastDirection = lastDirection;
            this.times = times;
            this.tempK = tempK;
        }

        public Point add(Point p, int lastDirection, int times, int tempK) {
            return new Point(x + p.x, y + p.y, lastDirection, times, tempK);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", lastDirection=" + lastDirection +
                    ", times=" + times +
                    ", tempK=" + tempK +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0), // 왼쪽
            new Point(0, -1), // 위
            new Point(1, 0), // 오른쪽
            new Point(0, 1), // 아래
    };

    // 한 정점에서 몇칸까지 가는지 미리 체크하기?
    /*
    왜냐하면 DFS를 사용할 경우에는,
    일단 쭉 깊게 파고 들어가니까 다른 경우의 수가 존재할 것을 고려해서 재차 방문도 할 수 있겠지만
    애초에 BFS를 사용함으로써 가장 먼저 방문된 경우가 최단 시간을 보장해주는 것 아니겠는가?
    쭉 깊게 들어가는 것이 아니라, 한 칸씩 한 칸씩 뻗어나가니까 당연히 먼저 만나면
    그게 가장 최단 시간이란 것을 깨닫지 못한 무지함이었던 것이다.
     */
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();
        int K = scan.nextInt();

        char[][] arr = new char[Y][X];
        for(int y = 0; y < Y; y++) {
            String line = scan.next();
            for(int x = 0; x < X; x++) {
                arr[y][x] = line.charAt(x);
            }
        }

        int startY = scan.nextInt() - 1;
        int startX = scan.nextInt() - 1;
        int finishY = scan.nextInt() - 1;
        int finishX = scan.nextInt() - 1;

        Point start = new Point(startX, startY);
        Point finish = new Point(finishX, finishY);

        int[][] visited = new int[Y][X];


    }
}