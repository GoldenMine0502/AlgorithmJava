package kr.goldenmine.baekjoon.platinum.platinum4.p2842;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
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

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Point[] directions = {
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(-1, 1),
            new Point(-1, -1),
            new Point(1, 1),
            new Point(1, -1),
    };

    static boolean simulate(Point start, int N, char[][] data, int[][] height, int Ks, int min, int max) {
        boolean[][] visited = new boolean[N][N];

        Queue<Point> q = new LinkedList<>();

        if(min <= height[start.y][start.x] && height[start.y][start.x] <= max) {
            q.add(start);
            visited[start.y][start.x] = true;
        }

        int ks = 0;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (data[p.y][p.x] == 'K') {
                ks++;
            }

            for (Point direction : directions) {
                Point next = new Point(p.x + direction.x, p.y + direction.y);
                if (next.x < 0 || next.x >= N || next.y < 0 || next.y >= N) continue;

                int nextHeight = height[next.y][next.x];

                if (!visited[next.y][next.x]) {
                    visited[next.y][next.x] = true;
                    if (min <= nextHeight && nextHeight <= max) {
                        q.add(next);
                    }
                }
            }
        }

        return Ks == ks;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        char[][] data = new char[N][N];
        int[][] height = new int[N][N];

        Point start = null;
        int Ks = 0;

        for (int y = 0; y < N; y++) {
            String line = scan.nextString();
            for (int x = 0; x < N; x++) {
                data[y][x] = line.charAt(x);
                if (data[y][x] == 'P') {
                    start = new Point(x, y);
                }
                if (data[y][x] == 'K') {
                    Ks++;
                }
            }
        }

        int[] height1 = new int[N * N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                height[y][x] = scan.nextInt();
                height1[y * N + x] = height[y][x];
            }
        }

        Arrays.sort(height1);

        int min = 9999_9999;
        int left = 0;
        int right = 0;
        while(right < N * N) {
            while(simulate(start, N, data, height, Ks, height1[left], height1[right])) {
                min = Math.min(min, height1[right] - height1[left]);
                left++;
                if(left >= N * N || left > right) break;
            }
            right++;
        }
        System.out.println(min);
    }
}
