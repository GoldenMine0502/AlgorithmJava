package kr.goldenmine.baekjoon.gold.gold4.p3055;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        int r;
        int c;
        int depth;

        Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", depth=" + depth +
                    '}';
        }
    }

    static Point[] directions = {
            new Point(1, 0, 0),
            new Point(-1, 0, 0),
            new Point(0, -1, 0),
            new Point(0, 1, 0),
    };

    static void water(int R, int C, int[][] map, boolean[][] visited, List<Point> waters) {
        List<Point> queue = new ArrayList<>();
        queue.addAll(waters);

        // 한번씩 확장해주자
        for(Point p : queue) {
            for(Point direction : directions) {
                Point next = new Point(p.r + direction.r, p.c + direction.c, 0);

                if(next.r < 0 || next.c < 0 || next.r >= R || next.c >= C) continue;

                if(map[next.r][next.c] == 'X' || map[next.r][next.c] == 'D') continue;

                if(!visited[next.r][next.c]) {
                    map[next.r][next.c] = '*'; // 물로 채우기
                    waters.add(next);
                    visited[next.r][next.c] = true;
                }
            }
        }
    }

    static boolean epoch(int R, int C, int[][] map, boolean[][] visited, Queue<Point> queue) {
        int n = queue.size();

        for(int i = 0; i < n; i++) {
            Point p = queue.poll();

//            System.out.println(p);

            if(map[p.r][p.c] == 'D') {
                return true;
            }

            if(map[p.r][p.c] == '*') {
                continue;
            }

            for(Point direction : directions) {
                Point next = new Point(p.r + direction.r, p.c + direction.c, p.depth + 1);

                if(next.r < 0 || next.c < 0 || next.r >= R || next.c >= C) continue;

                if(map[next.r][next.c] == 'X' || map[next.r][next.c] == '*') continue;

                if(!visited[next.r][next.c]) {
                    visited[next.r][next.c] = true;

                    queue.add(next);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int R = scan.nextInt();
        int C = scan.nextInt();

        int[][] map = new int[R][C];

        Point start = null;
        Point end = null;
        List<Point> waters = new ArrayList<>();

        boolean[][] visited = new boolean[R][C];

        for(int r = 0; r < R; r++) {
            String line = scan.nextString();
            for(int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);

                if(map[r][c] == 'S') {
                    start = new Point(r, c, 0);
                    visited[start.r][start.c] = true;
                }
                if(map[r][c] == 'D') {
                    end = new Point(r, c, 0);
                }
                if(map[r][c] == '*') {
                    waters.add(new Point(r, c, 0));
                    visited[r][c] = true;
                }
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        int epoch = 0;
        while(true) {
            water(R, C, map, visited, waters);
            boolean res = epoch(R, C, map, visited, queue);

            if(res) {
                System.out.println(epoch);
                return;
            }
            epoch++;

            if(epoch % 100 == 0) {
                System.gc();
            }

            if(epoch >= 5000) {
                System.out.println("KAKTUS");
                return;
            }
        }
    }
}
