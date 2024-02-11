package kr.goldenmine.baekjoon.platinum.platinum5.p3197_2S;

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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static Point[] directions = new Point[]{
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1),
    };

    static Queue<Point> queue = new LinkedList<>();
    static Queue<Point> queueBird = new LinkedList<>();

    public static void bfs(char[][] arr, int X, int Y, boolean[][] visited) {
        Queue<Point> points = new LinkedList<>();

//        points.add(start);
//        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

//            System.out.println("BFS: " + p);
            // 물로 바꾸기
            if(arr[p.y][p.x] == 'X')
                arr[p.y][p.x] = '.';

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (!visited[next.y][next.x]) {
                        visited[next.y][next.x] = true;
                        if (arr[next.y][next.x] == 'X') { // 다음 큐에 대기
                            points.add(next);
                        } else { // 계속 탐색
                            queue.add(next);
                        }
                    }
                }
            }
        }

        queue = points;
    }

    public static boolean bfsBird(char[][] arr, int X, int Y, boolean[][] visited) {
        Queue<Point> points = new LinkedList<>();

//        points.add(start);
//        visited[start.y][start.x] = true;

        while (!queueBird.isEmpty()) {
            Point p = queueBird.poll();

//            System.out.println(p);

//            System.out.println("BFSBird: " + p);
            // 물로 바꾸기
//            arr[p.y][p.x] = '.';

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (!visited[next.y][next.x]) {
                        visited[next.y][next.x] = true;
                        if (arr[next.y][next.x] == 'X') { // 다음 큐에 대기
                            points.add(next);
                        } else { // 계속 탐색
                            queueBird.add(next);
                        }

                        if (arr[next.y][next.x] == 'L') { // 백조 발견시 true 리턴
                            return true;
                        }
                    }
                }
            }
        }

        queueBird = points;

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        FastReader scan = new FastReader();

        int Y = scan.nextInt();
        int X = scan.nextInt();

        char[][] arr = new char[Y][X];

//        List<Point> birds = new ArrayList<>();

        for (int y = 0; y < Y; y++) {
            String text = scan.nextLine();

            for (int x = 0; x < X; x++) {
                arr[y][x] = text.charAt(x);
                if(arr[y][x] == '.' || arr[y][x] == 'L') {
                    queue.add(new Point(x, y));
                }
                if (arr[y][x] == 'L' && queueBird.isEmpty()) {
//                    System.out.println("add bird" + queueBird.size());
                    queueBird.add(new Point(x, y));
                    arr[y][x] = '.';
                }
            }
        }

        boolean[][] visited = new boolean[Y][X];
        boolean[][] visitedBirds = new boolean[Y][X];

//        for (int y = 0; y < Y; y++) {
//            for (int x = 0; x < X; x++) {
//                System.out.print(arr[y][x]);
//            }
//            System.out.println();
//        }
//        System.out.println();



//        if (find)

        bfs(arr, X, Y, visited);

        int day = 0;
        while (true) {
            boolean find = bfsBird(arr, X, Y, visitedBirds);
            if(find) break;

            bfs(arr, X, Y, visited);
            day++;

//            for (int y = 0; y < Y; y++) {
//                for (int x = 0; x < X; x++) {
//                    System.out.print(arr[y][x]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//
//            Thread.sleep(1000L);
        }
        System.out.println(day);
    }
}
