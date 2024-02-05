package kr.goldenmine.gold.gold5.p1107S;

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
        int value;
        int depth;
        boolean insert;

        public Point(int value, int depth, boolean insert) {
            this.value = value;
            this.depth = depth;
            this.insert = insert;
        }
    }

    public static int bfs(int N, List<Integer> possible, Point start) {
        ArrayDeque<Point> points = new ArrayDeque<>();

        boolean[] visited = new boolean[1000001];

        points.add(start); // insert=false -> +, -만 사용가능
//        points.add(new Point(0, 0, true));
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

            if(current.insert) { // 뒤에 추가 가능
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
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        Set<Integer> impossible = new HashSet<>();

        for (int i = 0; i < M; i++) {
            impossible.add(scan.nextInt());
        }

        List<Integer> possible = new ArrayList<>();
        for(int i = 0; i <= 9; i++) {
            if(impossible.contains(i)) continue;

            possible.add(i);
        }

//        System.out.println(set);

        ArrayDeque<Point> points = new ArrayDeque<>();

        int minimum = Integer.MAX_VALUE;

        int start = bfs(N, possible, new Point(100, 0, false));
        if(start >= 0) minimum = start;

        for(int i : possible) {
            int res = bfs(N, possible, new Point(i, 1, false));
            if(res >= 0 && minimum > res) {
                minimum = res;
            }
        }
        if(minimum != Integer.MAX_VALUE) {
            System.out.println(minimum);
        } else {
            System.out.println(-1);
        }
//        int result = bfs(N, set, -1, true);
//        int result2 = bfs(N, set, 100, false);
//
//        System.out.println(Math.min(result, result2));
    }
}
