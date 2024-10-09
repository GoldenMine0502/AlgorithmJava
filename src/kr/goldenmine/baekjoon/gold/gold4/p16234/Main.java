package kr.goldenmine.baekjoon.gold.gold4.p16234;

import java.util.*;

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
        int nextValue;
//        int depth;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point[] directions = new Point[] {
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0),
            new Point(0, -1),
    };

    static void bfs(int N, int L, int R, int[][] arr, Point start, boolean[][] visited, List<Point> change) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);

        List<Point> adds = new ArrayList<>();
        int sum = 0;
        int times = 0;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            int value = arr[current.y][current.x];

            sum += value;
            times++;
            adds.add(current);

            for(Point direction : directions) {
                Point next = new Point(current.x + direction.x, current.y + direction.y);

                // 좌표 벗어나면 스킵
                if(next.x < 0 || next.y < 0 || next.x >= N || next.y >= N) continue;

                int diff = Math.abs(arr[next.y][next.x] - value);

                if(L <= diff && diff <= R) {
                    if (!visited[next.y][next.x]) {
                        visited[next.y][next.x] = true;
                        queue.add(next);
                    }
                }
            }
        }
        if(adds.size() >= 2) {
            for (Point add : adds) {
                add.nextValue = sum / times;
                change.add(add);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int N = scan.nextInt();

        int L = scan.nextInt();
        int R = scan.nextInt();

        int[][] arr = new int[N][N];
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        int epoch = 0;
        while(true) {
            List<Point> changes = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];

            for(int y = 0; y < N; y++) {
                for(int x = 0; x < N; x++) {
                    if(!visited[y][x]) {
                        visited[y][x] = true;
                        bfs(N, L, R, arr, new Point(x, y), visited, changes);
                    }
                }
            }

            if(changes.isEmpty()) break;

            for(Point change : changes) {
                arr[change.y][change.x] = change.nextValue;
            }

            epoch++;
        }

        System.out.println(epoch);
    }
}
