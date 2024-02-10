package kr.goldenmine.gold.gold1.p1175;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
        int depth;
        int lastDirection;

        public Point(int x, int y, int depth, int lastDirection) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.lastDirection = lastDirection;
        }

        int reversedDirection() {
            if(lastDirection == 0) {
                return 2;
            } else if(lastDirection == 2) {
                return 0;
            } else if(lastDirection == 1) {
                return 3;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", lastDirection=" + lastDirection +
                    '}';
        }
    }

    public static int bfs(Point start, Point finish, int N, int M, int[][] area, boolean[][][] visited) {
        Queue<Point> queue = new LinkedList<>();
        Point[] directions = new Point[] {
                new Point(1, 0,0, 0),
                new Point(0, 1, 0, 1),
                new Point(-1, 0, 0, 2),
                new Point(0, -1, 0, 3),
        };

        for(int i = 0; i < 4; i++) {
            Point direction = directions[i];
            Point next = new Point(
                    start.x + direction.x,
                    start.y + direction.y,
                    1,
                    direction.lastDirection
            );

            if(next.x < 0 || next.x >= N || next.y < 0 || next.y >= M) continue;
            if(area[next.y][next.x] == '#') continue;

            queue.add(next);
        }

        visited = new boolean[N][M][1 << 4];

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            if(area[p.y][p.x] == '.')
                area[p.y][p.x] = p.depth;
            System.out.println(p);

            if(p.x == finish.x && p.y == finish.y) {
                return p.depth;
            }

            for(int i = 0; i < directions.length; i++) {
                Point direction = directions[i];

                Point next = new Point(
                        p.x + direction.x,
                        p.y + direction.y,
                        p.depth + 1,
                        direction.lastDirection
                );
                if(p.lastDirection == next.lastDirection) continue;
                if(next.x < 0 || next.x >= N || next.y < 0 || next.y >= M) continue;
                if(area[next.y][next.x] == '#') continue;

                int currentVisit = (next.lastDirection << 2) | p.lastDirection;
//                System.out.println(currentVisit);

                if(!visited[next.y][next.x][currentVisit]) {
                    visited[next.y][next.x][currentVisit] = true;

                    queue.add(next);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[][] area = new int[N][M];

        Point start = null;
        Point finish1 = null;
        Point finish2 = null;

        int count = 0;

        for(int y = 0; y < N; y++) {
            String str = scan.next();

            for(int x = 0; x < str.length(); x++) {
                area[y][x] = str.charAt(x);
                if(area[y][x] == 'S')
                    start = new Point(x, y, 0, -1);
                if(area[y][x] == 'C') {
//                    area[i][j] = ++count;
                    if(count == 0) {
                        finish1 = new Point(x, y, 0, -1);
                        count++;
                    } else {
                        finish2 = new Point(x, y, 0, -1);
                    }
                }
                // 0 = 오른쪽, 1 = 아래, 2 = 왼쪽, 3 = 위쪽
            }
        }

        System.out.println((int)'#');

        boolean[][][] visited = new boolean[N][M][4];
        boolean[][][] visited2 = new boolean[N][M][4];
        boolean[][][] visited3 = new boolean[N][M][4];
        boolean[][][] visited4 = new boolean[N][M][4];

//        int stoc1 = bfs(start, finish1, N, M, area, visited);
//        System.out.println(stoc1);
        int c1toc2 = bfs(finish1, finish2, N, M, area, visited3);
        System.out.println(c1toc2);

        for(int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(area[y][x] != '#' && area[y][x] != '.')
                    System.out.print(area[y][x] + "\t");
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }
//

//        int stoc2 = bfs(start, finish2, N, M, area, visited2);
//        System.out.println(stoc2);
//        int c2toc1 = bfs(finish2, finish1, N, M, area, visited4);
//        System.out.println(c2toc1);
    }
}
