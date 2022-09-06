package kr.goldenmine.gold.gold5.p16928;

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

        public Point(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }

    public static int bfs(List<List<Integer>> nodes) {
        ArrayDeque<Point> points = new ArrayDeque<>();
        points.addLast(new Point(1, 0));

        boolean[] visited = new boolean[101];

        while(!points.isEmpty()) {
            Point node = points.removeFirst();

            if(node.value == 100) {
                return node.depth;
            }

            if(nodes.get(node.value).size() > 0) { // 사다리 또는 뱀을 타고 이동
                int next = nodes.get(node.value).get(0);
                points.addFirst(new Point(next, node.depth)); // depth 변화 없음
                visited[next] = true;
            } else {
                for(int i = 1; i <= 6; i++) {
                    if(node.value <= 100 - i && !visited[node.value + i]) {
                        visited[node.value + i] = true;
                        points.addLast(new Point(node.value + i, node.depth + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i <= 100; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < N + M; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            nodes.get(start).add(finish);
        }

        int result = bfs(nodes);

        System.out.println(result);
    }
}
