package kr.goldenmine.gold.gold5.p13549S_01bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
        int sec;

        public Point(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.addLast(new Point(N, 0));

        boolean[] visited = new boolean[200001];

        while(!queue.isEmpty()) {
            Point p = queue.pollFirst();

            if(p.x == K) {
                System.out.println(p.sec);
                return;
            }

            // N 최대치 곱하기 2 정도면 뭐...
            if(p.x <= 100000 && !visited[2 * p.x]) {
                visited[2 * p.x] = true;
                queue.addLast(new Point(2 * p.x, p.sec));
            }

            if(p.x >= 1 && !visited[p.x - 1]) {
                visited[p.x - 1] = true;
                queue.addLast(new Point(p.x - 1, p.sec + 1));
            }

            if(p.x < 200000 && !visited[p.x + 1]) {
                visited[p.x + 1] = true;
                queue.addLast(new Point(p.x + 1, p.sec + 1));
            }
        }
    }
}
