package kr.goldenmine.silver.silver1.p1926;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
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

    public static int bfs(int X, int Y, boolean[][] visited, Point start, Predicate<Point> isQueueAdd, Consumer<Point> onPolled) {
        Queue<Point> points = new LinkedList<>();

        int size = 0;

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            onPolled.accept(p);
            size++;

            for (int i = 0; i < 4; i++) {
                Point next = p.add(directions[i]);

                if (next.x >= 0 && next.x < X && next.y >= 0 && next.y < Y) {
                    if (isQueueAdd.test(next)) {
                        if (!visited[next.y][next.x]) {
                            visited[next.y][next.x] = true;
                            points.add(next);
                        }
                    }
                }
            }
        }

        return size;
    }

    public static void bfs(int[][] arr, int X, int Y) {
        boolean[][] visited = new boolean[Y][X];

        int count = 0;

        int max = 0;

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (!visited[y][x] && arr[y][x] == 1) {
                    count++;
                    int value = arr[y][x];
                    visited[y][x] = true;

                    int size = bfs(X, Y, visited, new Point(x, y), it -> arr[it.y][it.x] == value, it -> { });

                    max = Math.max(max, size);
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int Y = scan.nextInt();
        int X = scan.nextInt();

        int[][] arr = new int[Y][X];

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                arr[y][x] = scan.nextInt();
            }
        }

        bfs(arr, X, Y);
    }
}
