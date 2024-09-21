package kr.goldenmine.baekjoon.gold.gold1.p27115;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int R = scan.nextInt();
        int C = scan.nextInt();
        int K = scan.nextInt();

        Point[] points = new Point[K];
        for(int i = 0; i < K; i++) {
            points[i] = new Point(scan.nextInt(), scan.nextInt(), scan.nextInt());
        }

        int[][] map = new int[R + 1][C + 1];
        for(int r = 1; r <= R; r++) {
            for(int c = 1; c <= C; c++) {
                map[r][c] = -1;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        for (Point p : points) {
            map[p.y][p.x] = p.depth;
            queue.add(p);
        }

        Point[] directions = {
                new Point(1, 0, -1),
                new Point(0, 1, -1),
                new Point(-1, 0, -1),
                new Point(0, -1, -1),
        };

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(Point direction : directions) {
                Point next = new Point(current.x + direction.x, current.y + direction.y, current.depth - 1);

                if(next.x <= 0 || next.y <= 0 || next.x > C || next.y > R) continue;

                System.out.println(next);

                if(map[next.y][next.x] < next.depth) {
                    map[next.y][next.x] = next.depth;
                    queue.add(next);
                }
            }
        }
        int count = 0;

        for(int r = 1; r <= R; r++) {
            for(int c = 1; c <= C; c++) {
                if(map[r][c] > -1) {
                    count++;
                    System.out.println(map[r][c] + " ");
                }
            }
        }
        System.out.println(count);
    }
}
