package kr.goldenmine.platinum.platinum1.p17165;

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
        int start;
        int finish;
        int depth;
        List<Integer> previousVisited;

        public Point(int start, int finish, int depth) {
            this.start = start;
            this.finish = finish;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "start=" + start +
                    ", finish=" + finish +
                    ", depth=" + depth +
                    '}';
        }
    }

    public static void bfs(int N, List<List<Integer>> oppositesList, int[][] results, int start) {

        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        // 초기 시작
        List<Integer> finishes = oppositesList.get(start);
        for(int i = 0; i < finishes.size(); i++) {
            queue.add(new Point(start, finishes.get(i), 1));
            visited[finishes.get(i)] = true;
        }

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            results[start][p.finish] = p.depth;

            List<Integer> nexts = oppositesList.get(p.finish);
            for(int i = 0; i < nexts.size(); i++) {
                Point next = new Point(p.finish, nexts.get(i), p.depth + 1);

                if(!visited[next.finish]) {
                    visited[next.finish] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] opposites = new int[N][N];
        int[][] results = new int[N][N];

        for(int i = 0; i < results.length; i++) {
            Arrays.fill(results[i], 9000);
        }

        for(int j = 0; j < N; j++) {
            String text = scan.next();
            for(int i = 0; i < N; i++) {
                char ch = text.charAt(i);
                opposites[j][i] = ch;
            }
        }

        List<List<Integer>> oppositesList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            List<Integer> nexts = new ArrayList<>();
            for(int f = 0; f < N; f++) {
                if(opposites[i][f] == 'W') {
                    nexts.add(f);
//                    System.out.println(i + ", " + f);
                }
            }
            oppositesList.add(nexts);
        }

        for(int i = 0; i < N; i++) {
            bfs(N, oppositesList, results, i);
        }

//        for(int j = 0; j < N; j++) {
//            for(int i = 0; i < N; i++) {
//                System.out.print(results[j][i] + " ");
//            }
//            System.out.println();
//        }

        int global = -1;
        int globalMin = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            int localMax = 0;
            for(int j = 0; j < N; j++) {
                if(i != j)
                    localMax = Math.max(results[i][j], localMax);
            }

            if(globalMin > localMax) {
                globalMin = localMax;
                global = i;
            }
        }

        System.out.println(globalMin + " " + (global + 1));

    }
}
