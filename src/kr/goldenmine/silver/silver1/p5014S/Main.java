package kr.goldenmine.silver.silver1.p5014S;

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


    public static int bfs(int F, int S, int G, int U, int D) {
        boolean[] visited = new boolean[F + 1];
        int[] count = new int[F + 1];
        Queue<Integer> points = new LinkedList<>();

        points.add(S);
        visited[S] = true;

        while (!points.isEmpty()) {
            int p = points.poll();

            if(p == G) {
                return count[p];
            }

            if(p + U <= F && !visited[p + U]) {
                visited[p + U] = true;
                count[p + U] = count[p] + 1;
                points.add(p + U);
            }
            if(p - D >= 1 && !visited[p - D]) {
                visited[p - D] = true;
                count[p - D] = count[p] + 1;
                points.add(p - D);
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int F = scan.nextInt();
        int S = scan.nextInt();
        int G = scan.nextInt();
        int U = scan.nextInt();
        int D = scan.nextInt();

        int result = bfs(F, S, G, U, D);
        if(result == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(result);
        }
    }
}
