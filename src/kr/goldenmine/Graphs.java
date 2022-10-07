package kr.goldenmine;

import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Graphs {
    /*
    지금까지 BFS/DFS 공부하면서 알았던 지식

    BFS
    1. 한 정점에서 1칸씩 퍼지는 때 빼고도 여러개 queue에 추가하는 경우도 가능하다.
    2. 시작 지점이 여러개일 수 있다.
    3. 먼저 방문한 정점은 무조건 최단거리이다. (1번 상황을 적용하지 않은 경우)

    DFS
    1. DP와 응용해 depth로 캐싱이 가능하다.
    2. 먼저 방문한 정점이 최단거리가 아닐 수 있다.
     */

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

            for (int i = 0; i < directions.length; i++) {
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
