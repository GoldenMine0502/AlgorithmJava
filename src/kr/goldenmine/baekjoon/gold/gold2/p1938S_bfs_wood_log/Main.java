package kr.goldenmine.baekjoon.gold.gold2.p1938S_bfs_wood_log;

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

        boolean horizontal;

        int depth;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, boolean horizontal, int depth) {
            this(x, y);
            this.horizontal = horizontal;
            this.depth = depth;
        }

        public Point add(Point p, boolean horizontal, int depth) {
            return new Point(x + p.x, y + p.y, horizontal, depth);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", horizontal=" + horizontal +
                    ", depth=" + depth +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static Point zero = new Point(0, 0);
    static Point plusX1 = new Point(1, 0);
    static Point minusX1 = new Point(-1, 0);
    static Point plusY1 = new Point(0, 1);
    static Point minusY1 = new Point(0, -1);

    public static int bfs(int X, int Y, char[][] arr, boolean[][][] visited, Point start, Point finish) {
        final int HORIZONTAL = 0;
        final int VERTICAL = 1;

        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x][start.horizontal ? HORIZONTAL : VERTICAL] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

//            System.out.println(p);

            // 통나무 상태도 같고
            if(p.horizontal == finish.horizontal) {
                // 위치도 같은 경우 리턴
                if(p.x == finish.x && p.y == finish.y) {
                    return p.depth;
                }
            }

            for (int i = 0; i < 5; i++) {
                // 0,1,2,3번 -> 상하좌우
                // 4번 -> 방향 바꾸기
                Point next = i < 4 ? p.add(directions[i], p.horizontal, p.depth + 1) : new Point(p.x, p.y, !p.horizontal, p.depth + 1);

                Point nextPlusX1 = next.add(plusX1, next.horizontal, 0);
                Point nextMinusX1 = next.add(minusX1, next.horizontal, 0);
                Point nextPlusY1 = next.add(plusY1, next.horizontal, 0);
                Point nextMinusY1 = next.add(minusY1, next.horizontal, 0);

                if(i == 4) {
                    // 해당 좌표 기준 3 * 3이 모두 비어있는지 확인, 하나라도 비어있지 않다면 continue;
                    boolean exists = true;

                    for(int dy = -1; dy <= 1; dy++) {
                        for(int dx = -1; dx <= 1; dx++) {
                            int existX = next.x + dx;
                            int existY = next.y + dy;

                            if(!(existX >= 0 && existX < X && existY >= 0 && existY < Y) || arr[existY][existX] == '1') {
                                exists = false;
                                break;
                            }
                        }
                    }
//                    System.out.println(next + ", " + exists);

                    if(!exists) continue;
                }

                // 0이 빈 곳
                // 1이 벽
                if(next.horizontal) {
                    if (next.x >= 1 && next.x < X - 1 && next.y >= 0 && next.y < Y) {
                        // 다음 위치에서 나머지 두 통나무 끝도 들어갈 수 있는 경우(가로 기준)
                        if(arr[next.y][next.x] == '0' && arr[nextPlusX1.y][nextPlusX1.x] == '0' && arr[nextMinusX1.y][nextMinusX1.x] == '0') {
                            if (!visited[next.y][next.x][HORIZONTAL]) {
                                visited[next.y][next.x][HORIZONTAL] = true;
                                points.add(next);
                            }
                        }
                    }
                } else {
                    if (next.x >= 0 && next.x < X && next.y >= 1 && next.y < Y - 1) {
                        // 다음 위치에서 나머지 두 통나무 끝도 들어갈 수 있는 경우(세로 기준)
                        if(arr[next.y][next.x] == '0' && arr[nextPlusY1.y][nextPlusY1.x] == '0' && arr[nextMinusY1.y][nextMinusY1.x] == '0') {
                            if (!visited[next.y][next.x][VERTICAL]) {
                                visited[next.y][next.x][VERTICAL] = true;
                                points.add(next);
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<Point> starts = new ArrayList<>();
        List<Point> finishes = new ArrayList<>();

        char[][] arr = new char[N][N];

        for(int y = 0; y < N; y++) {
            String line = scan.next();
            for(int x = 0; x < N; x++) {
                arr[y][x] = line.charAt(x);

                if(arr[y][x] == 'B') {
                    arr[y][x] = '0';
                    starts.add(new Point(x, y));
                }
                if(arr[y][x] == 'E') {
                    arr[y][x] = '0';
                    finishes.add(new Point(x, y));
                }
            }
        }

        // 두 x값에 차이가 있다면 방향은 수평으로 확정할 수 있다.
        boolean startHorizontal = (starts.get(0).x - starts.get(1).x) != 0;
        boolean finishHorizontal = (finishes.get(0).x - finishes.get(1).x) != 0;

        // 0번 인덱스 = 수평, 1번 인덱스 = 수직
        boolean[][][] visited = new boolean[N][N][2];

        Point start = new Point(starts.get(1).x, starts.get(1).y, startHorizontal, 0);
        Point finish = new Point(finishes.get(1).x, finishes.get(1).y, finishHorizontal, 0);

//        System.out.println(start);
//        System.out.println(finish);

        int result = bfs(N, N, arr, visited, start, finish);
        System.out.println(result);
    }
}
