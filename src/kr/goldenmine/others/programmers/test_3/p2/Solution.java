package kr.goldenmine.others.programmers.test_3.p2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class Point {
        int x1;
        int y1;
        int x2;
        int y2;
        int depth;

        public Point(int x1, int y1, int x2, int y2, int depth) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x1=" + x1 +
                    ", y1=" + y1 +
                    ", x2=" + x2 +
                    ", y2=" + y2 +
                    ", depth=" + depth +
                    '}';
        }
    }

    static Point[] directions = {
            new Point(1, 0, 1, 0, 123456789),
            new Point(1, 0, 0, 1, 123456789),
            new Point(1, 0, -1, 0, 123456789),
            new Point(1, 0, 0, -1, 123456789),
            new Point(0, 1, 1, 0, 123456789),
            new Point(0, 1, 0, 1, 123456789),
            new Point(0, 1, -1, 0, 123456789),
            new Point(0, 1, 0, -1, 123456789),
            new Point(-1, 0, 1, 0, 123456789),
            new Point(-1, 0, 0, 1, 123456789),
            new Point(-1, 0, -1, 0, 123456789),
            new Point(-1, 0, 0, -1, 123456789),
            new Point(0, -1, 1, 0, 123456789),
            new Point(0, -1, 0, 1, 123456789),
            new Point(0, -1, -1, 0, 123456789),
            new Point(0, -1, 0, -1, 123456789),
    };

    public int bfs(int[][] maze) {
        int Y = maze.length;
        int X = maze[0].length;

        // 시작점 찾기
        Point start = new Point(-1, -1, -1, -1, 0);
        for(int y = 0; y < Y; y++) {
            for(int x = 0; x < X; x++) {
                if(maze[y][x] == 1) {
                    start.x1 = x;
                    start.y1 = y;
                }
                if(maze[y][x] == 2) {
                    start.x2 = x;
                    start.y2 = y;
                }
            }
        }

//        System.out.println(start);

        // BFS 시작
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        boolean[][][][][] visited = new boolean[Y][X][Y][X][16];
        for(int i = 0; i < 16; i++) {
            visited[start.y1][start.x1][start.y2][start.x2][i] = true;
        }

        while(!queue.isEmpty()) {
            Point point = queue.poll();
//            System.out.println(point);

            if(maze[point.y1][point.x1] == 3 && maze[point.y2][point.x2] == 4) {
                return point.depth;
            }

            for(int i = 0; i < directions.length; i++) {
                Point direction = directions[i];

                Point next = new Point(point.x1 + direction.x1, point.y1 + direction.y1, point.x2 + direction.x2, point.y2 + direction.y2, point.depth + 1);

                // 범위 벗어나면 안됨
                if(next.x1 < 0 || next.x1 >= X) continue;
                if(next.x2 < 0 || next.x2 >= X) continue;
                if(next.y1 < 0 || next.y1 >= Y) continue;
                if(next.y2 < 0 || next.y2 >= Y) continue;

                // 벽 못뚫음
                if(maze[next.y1][next.x1] == 5) continue;
                if(maze[next.y2][next.x2] == 5) continue;

//                System.out.println(point + " (can) " + next);

                // 같은 위치에 둘 다 있을 순 없음
                if(next.x1 == next.x2 && next.y1 == next.y2) continue;

                // 교차 안됨
                if(point.x1 == next.x2 && point.y1 == next.y2) continue;
                if(point.x2 == next.x1 && point.y2 == next.y1) continue;


                if(!visited[next.y1][next.x1][next.y2][next.x2][i]) {
                    visited[next.y1][next.x1][next.y2][next.x2][i] = true;
//                    System.out.println(point + " > " + next);
                    queue.add(next);
                }
            }
            System.out.println();
        }
        return 0;
    }

    public int solution(int[][] maze) {
        int answer = bfs(maze);

        return answer;
    }

    public static void main(String[] args) {
//        int[][] maze = {{1, 4}, {0, 0}, {2, 3}};
        int[][] maze =  {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}};



        System.out.println(new Solution().solution(maze));
    }
}
