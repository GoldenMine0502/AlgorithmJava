package kr.goldenmine.gold.gold5.p1107;

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
        boolean digits;

        public Point(int value, int depth, boolean digits) {
            this.value = value;
            this.depth = depth;
            this.digits = digits;
        }
    }

    public static int bfs(int N, Set<Integer> unavailable, int start, boolean digits) {
        ArrayDeque<Point> points = new ArrayDeque<>();

        boolean[] visited = new boolean[Math.max(N * 2 + 1, 100 * 2 + 1)];
//        Arrays.fill(visited, -1);

        points.add(new Point(start, 0, digits)); // 처음부터 누르는 경우
//        points.add(new Point(100, 0, false)); // 기존에서 바꾸는경우

        while (!points.isEmpty()) {
            Point node = points.removeFirst();

//            if(node.value == 100000 || node.value == 10000 || node.value == 1000 || node.value == 100 || node.value == 1 || node.value == 10)
//                System.out.println(node.value + ", " + node.depth);

            if (node.value == N) {
                return node.depth;
            }

            // 0~9숫자 붙이기
            if (node.digits) {
                for (int i = 0; i <= 9; i++) {
                    int next = (node.value != -1 ? node.value * 10 : 0) + i;
                    if (next > 0 && next < visited.length && !visited[next] && !unavailable.contains(i)) {
                        visited[next] = true;
                        points.addLast(new Point(next, node.depth + 1, true));
                    }
                }
            }

            // +1, -1 하기, 한번이라도 +1, -1을 하면 더이상 digit을 올릴 수는 없음
            if(node.value != -1) {
                for (int i = -1; i <= 1; i += 2) {
                    int next = node.value + i;
                    if (next >= 0 && next < visited.length && !visited[next]) {
                        visited[next] = true;
                        points.addLast(new Point(next, node.depth + 1, false));
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

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < M; i++) {
            set.add(scan.nextInt());
        }

//        System.out.println(set);

        int result = bfs(N, set, -1, true);
        int result2 = bfs(N, set, 100, false);

        System.out.println(Math.min(result, result2));
    }
}
