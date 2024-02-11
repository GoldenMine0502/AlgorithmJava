package kr.goldenmine.baekjoon.gold.gold4.p12869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
        int hp1;
        int hp2;
        int hp3;
        int depth;

        public Point(int hp1, int hp2, int hp3, int depth) {
            this.hp1 = hp1;
            this.hp2 = hp2;
            this.hp3 = hp3;
            this.depth = depth;
        }
    }

    public static int bfs(int[] scvs, int N) {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(scvs[0], scvs[1], scvs[2], 0));

        Point[] nexts = new Point[] { // 가능한 모든 경우의 수 목록 3P3
            new Point(9, 3, 1, 0),
            new Point(9, 1, 3, 0),
            new Point(3, 9, 1, 0),
            new Point(3, 1, 9, 0),
            new Point(1, 9, 3, 0),
            new Point(1, 3, 9, 0),
        };

        final int plus = 0;
        boolean[][][] visited = new boolean[61][61][61];

        while(!points.isEmpty()) {
            Point p = points.poll();

            if(p.hp1 <= 0 && p.hp2 <= 0 && p.hp3 <= 0) {
                return p.depth;
            }

            for(int i = 0; i < nexts.length; i++) {
                Point direction = nexts[i];

                if(p.hp1 <= 0 && direction.hp1 == 9) continue;
                if(p.hp2 <= 0 && direction.hp2 == 9) continue;
                if(p.hp3 <= 0 && direction.hp3 == 9) continue;

                Point next = new Point(p.hp1 - direction.hp1, p.hp2 - direction.hp2, p.hp3 - direction.hp3, p.depth + 1);

                if(next.hp1 < 0) next.hp1 = 0;
                if(next.hp2 < 0) next.hp2 = 0;
                if(next.hp3 < 0) next.hp3 = 0;

                if(!visited[next.hp1 + plus][next.hp2 + plus][next.hp3 + plus]) {
                    visited[next.hp1 + plus][next.hp2 + plus][next.hp3 + plus] = true;

                    points.add(next);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[] scvs = new int[3];
        for(int i = 0; i < N; i++) {
            scvs[i] = scan.nextInt();
        }

        int res = bfs(scvs, N);

        System.out.println(res);
    }
}
