package kr.goldenmine;

import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Graphs {
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

    public static void bfs(int X, int Y, boolean[][] visited, Point start, Predicate<Point> isQueueAdd, Consumer<Point> onPolled) {
        Queue<Point> points = new LinkedList<>();

        points.add(start);
        visited[start.y][start.x] = true;

        while (!points.isEmpty()) {
            Point p = points.poll();

            onPolled.accept(p);

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
    }
}
