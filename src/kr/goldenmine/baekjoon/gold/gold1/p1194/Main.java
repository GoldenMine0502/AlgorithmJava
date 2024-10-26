package kr.goldenmine.baekjoon.gold.gold1.p1194;

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
        int took;

        Point(int x, int y, int depth, int took) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.took = took;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", took=" + took +
                    '}';
        }
    }

    static Point[] directions = {
            new Point(1, 0, 0, 0),
            new Point(-1, 0, 0, 0),
            new Point(0, -1, 0, 0),
            new Point(0, 1, 0,0),
    };

    static int bfs(int N, int M, int[][] map, Point start) {
        boolean[][][] visited = new boolean[N][M][1 << 8];

        // F  E  D  C  B  A  0
        // 64 32 16 8  4  2  1

        Queue<Point> queue = new LinkedList<Point>();
        queue.add(start);
        visited[start.y][start.x][0] = true;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            if(map[p.y][p.x] == '1') {
                return p.depth;
            }

            for(Point direction : directions) {
                Point next = new Point(p.x + direction.x, p.y + direction.y, p.depth + 1, p.took);

                if(next.x < 0 || next.y < 0 || next.x >= M || next.y >= N) continue;

                int nextMap = map[next.y][next.x];

                // 벽은 스킵
                if(nextMap == '#') continue;

                // 아직 가지지 않은 상태가 존재한다면 스킵
                if('A' <= nextMap && nextMap <= 'F') {
                    boolean hasKey = (next.took & (1 << (nextMap - 'A' + 1))) > 0;
//                    System.out.println("skip " + next);
                    if(!hasKey) continue;
                }

                // 키를 습득하기
                if('a' <= nextMap && nextMap <= 'f') {
//                    System.out.println("take " + next);
                    next.took |= (1 << (nextMap - 'a' + 1));
                }

//                System.out.println(next + ", " + visited[next.y][next.x][next.took] + ", " + next.took);

                // visited 상태가 내가 키를 갖고 있는 상태와 달라야 함
                if(!visited[next.y][next.x][next.took]) {
                    visited[next.y][next.x][next.took] = true;
                    queue.add(next);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] map = new int[N][M];

        Point start = null;

        for(int y = 0; y < N; y++) {
            String line = scan.nextString();
            for(int x = 0; x < M; x++) {
                map[y][x] = line.charAt(x);

                if(map[y][x] == '0') {
                    start = new Point(x, y, 0, 0);
                }
            }
        }

//        System.out.println(start);
        int res = bfs(N, M, map, start);

        System.out.println(res);
    }
}
