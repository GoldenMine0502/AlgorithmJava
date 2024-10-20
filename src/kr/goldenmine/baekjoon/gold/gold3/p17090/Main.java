package kr.goldenmine.baekjoon.gold.gold3.p17090;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int dfs(int y, int x, int N, int M, int[][] map) {
        int res = 0;

        // up
        if(y > 0 && map[y - 1][x] == 'D') res += dfs(y - 1,    x    , N, M, map) + 1;
        // down
        if(y < N && map[y + 1][x] == 'U') res += dfs(y + 1,    x    , N, M, map) + 1;
        // left
        if(x > 0 && map[y][x - 1] == 'R') res += dfs(   y    , x - 1, N, M, map) + 1;
        // down
        if(x < M && map[y][x + 1] == 'L') res += dfs(   y    , x + 1, N, M, map) + 1;

        return res;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] map = new int[N + 2][M + 2];

        for(int y = 1; y <= N; y++) {
            String line = scan.nextString();
            for(int x = 1; x <= M; x++) {
                map[y][x] = line.charAt(x - 1);
            }
        }

        int sum = 0;
        for(int x = 1; x <= M; x++) {
            sum += dfs(    0, x, N, M, map);
            sum += dfs(N + 1, x, N, M, map);
        }
        for(int y = 1; y <= N; y++) {
            sum += dfs(y,     0, N, M, map);
            sum += dfs(y, M + 1, N, M, map);
        }
        System.out.println(sum);
    }
}
