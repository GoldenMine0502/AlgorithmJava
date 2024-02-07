package kr.goldenmine.gold.gold5.p1107S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int value;
        int depth;
        boolean insert;

        public Point(int value, int depth, boolean insert) {
            this.value = value;
            this.depth = depth;
            this.insert = insert;
        }
    }

    static int bfs(int N, List<Integer> possible, Point start) {
        ArrayDeque<Point> points = new ArrayDeque<>();

        boolean[] visited = new boolean[1000001];

        points.add(start); // insert=false -> +, -만 사용가능
        visited[start.value] = true;

        for(int i : possible) {
            visited[i] = true;
            points.add(new Point(i, 1, true)); // insert=true -> 삽입 모드
        }

        while(!points.isEmpty()) {
            Point current = points.poll();

            if(current.value == N) {
                return current.depth;
            }

            if(current.insert) { // 뒤에 자릿수 추가 가능
                for(int i : possible) {
                    int nextValue = current.value * 10 + i;
                    if(nextValue >= 1000000) break;

                    if(!visited[nextValue]) {
                        visited[nextValue] = true;
                        Point next = new Point(nextValue, current.depth + 1, true);
                        points.add(next);
                    }
                }
            }

            // +
            if(current.value < 1000000 && !visited[current.value + 1]) {
                visited[current.value + 1] = true;
                Point next = new Point(current.value + 1, current.depth + 1, false);
                points.add(next);
            }

            // -
            if(current.value > 0 && !visited[current.value - 1]) {
                visited[current.value - 1] = true;
                Point next = new Point(current.value - 1, current.depth + 1, false);
                points.add(next);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();

        // 불가능한 숫자 목록
        Set<Integer> impossible = new HashSet<>();
        for (int i = 0; i < M; i++) {
            impossible.add(scan.nextInt());
        }

        // 가능한 숫자 목록
        List<Integer> possible = new ArrayList<>();
        for(int i = 0; i <= 9; i++) {
            if(impossible.contains(i)) continue;

            possible.add(i);
        }

        // 가능한 후보군 목록
        List<Point> starts = new ArrayList<>();
        starts.add(new Point(100, 0, false));

        for(int i : possible) {
            starts.add(new Point(i, 1, true));
        }

        // 최솟값 구하기
        int minimum = Integer.MAX_VALUE;

        for(Point p : starts) {
            int res = bfs(N, possible, p);
            if(res != -1 && res < minimum) {
                minimum = res;
            }
        }

        // 결과 출력
        if(minimum != Integer.MAX_VALUE) {
            System.out.println(minimum);
        } else {
            System.out.println(-1);
        }
    }
}
