package kr.goldenmine.baekjoon.gold.gold4.p1261;

import java.util.ArrayDeque;
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

        int X = scan.nextInt();
        int Y = scan.nextInt();

        int[][] arr = new int[Y][X];
        for(int i = 0; i < Y; i++) {
            String line = scan.nextString();
            for(int j = 0; j < X; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 0));

        boolean[][] visited = new boolean[Y][X];

        Point[] directions = {
                new Point(1, 0, 0),
                new Point(-1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, -1, 0),
        };

        while(!queue.isEmpty()) {
            Point current = queue.poll();

//            System.out.println(current);

            if(current.x == X - 1 && current.y == Y - 1) {
                System.out.println(current.depth);
                return;
            }

            for(Point direction : directions) {
                Point next = new Point(current.x + direction.x, current.y + direction.y, current.depth);

                if(next.x < 0 || next.y < 0 || next.x >= X || next.y >= Y) continue;


                if(!visited[next.y][next.x]) {
                    visited[next.y][next.x] = true;

                    if(arr[next.y][next.x] == '0') {
                        queue.addFirst(next);
                    } else { // 1이면 마지막에 서치하도록 수정
                        next.depth++;
                        queue.addLast(next);
                    }
                }
            }
        }
    }
}
