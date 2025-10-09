package kr.goldenmine.baekjoon.gold.gold2.p31404;

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
        int direction;
        int diggedCount;
        int depth;

        Point(int x, int y, int direction, int depth) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    ", diggedCount=" + diggedCount +
                    ", depth=" + depth +
                    '}';
        }
    }

    static Point[] directions = {
            new Point(0, -1, 0, 0), // 0도이면 위
            new Point(1, 0, 1, 0), // 90도이면 오른쪽
            new Point(0, 1, 2, 0), // 180도이면 아래
            new Point(-1, 0, 3, 0), // 180도이면 왼쪽
    };

    static int bfs(int Y, int X, int Y1, int X1, int D, int[][] ruleA, int[][] ruleB) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(X1, Y1, D, 0));

        boolean[][][] visited = new boolean[Y][X][4];

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            boolean digged = visited[current.y][current.x][0] |
                    visited[current.y][current.x][1] |
                    visited[current.y][current.x][2] |
                    visited[current.y][current.x][3];
            int nextDirectionIndex = current.direction + (digged ? ruleB[current.y][current.x] : ruleA[current.y][current.x]);
            nextDirectionIndex %= 4;

            Point nextDirection = directions[nextDirectionIndex];
            Point next = new Point(current.x + nextDirection.x, current.y + nextDirection.y, nextDirectionIndex, current.depth + 1);
            next.diggedCount = current.diggedCount;

            System.out.println(current + ", " + next);
            for(int y = 0; y < Y; y++) {
                for(int x = 0; x < X; x++) {
                    boolean dig = visited[y][x][0] |
                            visited[y][x][1] |
                            visited[y][x][2] |
                            visited[y][x][3];

                    if(dig) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();
            }
            System.out.println();

            if(next.x < 0 || next.y < 0 || next.x >= X || next.y >= Y) {
                return current.depth;
            }

            if(visited[current.y][current.x][nextDirectionIndex]) {
                if(next.diggedCount == 0) {
                    return current.depth;
                } else {
                    next.diggedCount = 0;
                }
            }

            if(!digged) {
                next.diggedCount++;
            }
            visited[current.y][current.x][nextDirectionIndex] = true;
            queue.add(next);
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int Y1 = scan.nextInt();
        int X1 = scan.nextInt();
        int D = scan.nextInt();

        int[][] ruleA = new int[Y][X];
        int[][] ruleB = new int[Y][X];

        for(int y = 0; y < Y; y++) {
            String line = scan.nextString();
            for(int x = 0; x < X; x++) {
                ruleA[y][x] = line.charAt(x);
            }
        }

        for(int y = 0; y < Y; y++) {
            String line = scan.nextString();
            for(int x = 0; x < X; x++) {
                ruleB[y][x] = line.charAt(x);
            }
        }

        int res = bfs(Y, X, Y1, X1, D, ruleA, ruleB);
        System.out.println(res);
    }
}
