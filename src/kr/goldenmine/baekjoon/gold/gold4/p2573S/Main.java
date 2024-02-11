package kr.goldenmine.baekjoon.gold.gold4.p2573S;

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

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point p) {
            return new Point(x + p.x, y + p.y);
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    public static void bfs(int Y, int X, int[][] map, boolean[][] visited, Point start) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

//            // 인접한 면적마다

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);

                // 상하좌우 반복
                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    // 다음 위치가 벽인 경우 녹이고 서칭 중지, 방문 여부와는 상관없다
                    if (map[next.y][next.x] > 0) {
                        map[next.y][next.x]--;
                        visited[next.y][next.x] = true;
                    } else {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void bfsCheck(int Y, int X, int[][] map, boolean[][] visited, Point start) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

//            // 인접한 면적마다

            for (int i = 0; i < directions.length; i++) {
                Point next = p.add(directions[i]);

                // 상하좌우 반복
                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    // 다음 위치가 벽인 경우 녹이고 서칭 중지, 방문 여부와는 상관없다
                    if (!visited[next.y][next.x] && map[next.y][next.x] > 0) {
                        visited[next.y][next.x] = true;
                        points.add(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
3 3
0 0 0
0 1 0
0 0 0

3 5
0 0 0 0 0
0 1 0 1 0
0 0 0 0 0
         */
        FastReader scan = new FastReader();

        int R = scan.nextInt();
        int C = scan.nextInt();

        int[][] map = new int[R][C];
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = scan.nextInt();
            }
        }
//        List<Point> starts = new ArrayList<>();
        int T = 0;
        while(true) {
            // 방문 초기화
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    visited[i][j] = false;
                }
            }

            // 얼음 녹이기
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (!visited[i][j] && map[i][j] == 0) {
                        bfs(R, C, map, visited, new Point(j, i));
                    }
//                bfs(R, C, map, visited, new Point(j, i));
                }
            }
            T++;


//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            scan.nextInt();


            // 덩어리가 두개 이상인지 확인
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    visited[i][j] = false;
                }
            }

            int count = 0;
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        bfsCheck(R, C, map, visited, new Point(j, i));
                        count++;
                    }
                }
            }
            if(count >= 2) {
                System.out.println(T);
                break;
            }
            if(count == 0) {
                System.out.println(0);
                break;
            }
        }

//        for(Point p : starts) {
//            if(!visited[p.y][p.x])
//                bfs(R, C, map, visited, p);
//        }

    }
}
