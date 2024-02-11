package kr.goldenmine.baekjoon.gold.gold5.p7569_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    static class Point3D {
        int x;
        int y;
        int h;
        int d;

        public Point3D(int x, int y, int h, int d) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.d = d;
        }

        public Point3D add(Point3D p, int d) {
            return new Point3D(x + p.x, y + p.y, h + p.h, d);
        }
    }

    static Point3D[] directions = new Point3D[]{
            new Point3D(-1, 0, 0, -1),
            new Point3D(1, 0, 0, -1),
            new Point3D(0, -1, 0, -1),
            new Point3D(0, 1, 0, -1),
            new Point3D(0, 0, 1, -1),
            new Point3D(0, 0, -1, -1),
    };

    public static void bfs(int X, int Y, int H, boolean[][][] visited, List<Point3D> starts, Predicate<Point3D> isQueueAdd, Consumer<Point3D> onPolled) {
        Queue<Point3D> points = new LinkedList<>();

        for(int i = 0; i < starts.size(); i++) {
            Point3D start = starts.get(i);
            points.add(start);
            visited[start.h][start.y][start.x] = true;
        }

        while (!points.isEmpty()) {
            Point3D p = points.poll();

            onPolled.accept(p);

            for (int i = 0; i < 6; i++) {
                Point3D next = p.add(directions[i], p.d + 1);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y && next.h >= 0 && next.h < H) {
                    if (isQueueAdd.test(next)) {
                        if (!visited[next.h][next.y][next.x]) {
                            visited[next.h][next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int X = scan.nextInt();
        int Y = scan.nextInt();
        int H = scan.nextInt();

        int[][][] arr = new int[H][Y][X];

        List<Point3D> starts = new ArrayList<>();

        for(int h = 0; h < H; h++) {
            for(int y = 0; y < Y; y++) {
                for(int x = 0; x < X; x++) {
                    arr[h][y][x] = scan.nextInt() - 1;
                    if(arr[h][y][x] == 0) {
                        starts.add(new Point3D(x, y, h, 0));
                    }
                }
            }
        }

        boolean[][][] visited = new boolean[H][Y][X];

        bfs(X, Y, H, visited, starts, it -> arr[it.h][it.y][it.x] == -1, it -> arr[it.h][it.y][it.x] = it.d);

        int max = 0;

        boolean succeed = true;

        for(int h = 0; h < H; h++) {
            for(int y = 0; y < Y; y++) {
                for(int x = 0; x < X; x++) {
//                    System.out.print(arr[h][y][x] + " ");
                    max = Math.max(arr[h][y][x], max);
                    if(arr[h][y][x] == -1) {
                        succeed = false;
                    }
                }
//                System.out.println();
            }
        }

        if(succeed) {
            System.out.println(max);
        } else {
            System.out.println(-1);
        }
    }
}
