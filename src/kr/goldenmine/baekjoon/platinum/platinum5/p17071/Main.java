package kr.goldenmine.baekjoon.platinum.platinum5.p17071;

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
        int x;
        int depth;

        Point(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }
    }

    public static int calcSpeed(int s, int f) {
        return (s + f) * (f - s + 1) / 2;
    }

    public static void bfsK(int[] ks, int K) {
//        int[] visited = new int[500001]; // 0~500000
//        Arrays.fill(visited, 999999);
        boolean[] visited = new boolean[500001];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(K, 0));

//        List<HashSet<Integer>> visited = new ArrayList<>();
//        for(int i = 0; i <= 500000; i++) {
//            visited.add(new HashSet<>());
//        }

//        int[] speedMap = new int[500001];
//        for(int i = 1; i <= 500000; i++) {
//
//        }

        while(!queue.isEmpty()) {
            Point current = queue.poll();

//            if(current.x <= 50) {
//                System.out.println(current.x + "=" + current.depth + ", " + ks[current.x] + ", " + (Math.abs(K - current.x) * 2 < current.depth));
//            }
            ks[current.x] = current.depth;
//            if(Math.abs(K - current.x) * 2 < current.depth) continue;

//            ks[current.x] = Math.min(ks[current.x], current.depth);

            // 1부터 depth + 1까지의 합 = (1 + depth + 1) * (depth + 1) / 2
            int nextSpeed = current.depth + 1;

//            int left = current.x - nextSpeed;
            int right = current.x + nextSpeed;
            // 왼쪽 이동
//            if(left >= 0 && !visited[left]) {
//                visited[left] = true;
//                queue.add(new Point(left, current.depth + 1));
//            }

            if(right <= 500000 && !visited[right]) {
                visited[right] = true;
                queue.add(new Point(right, current.depth + 1));
            }
        }

        // 0에서 속도 100, 1을 가려면 -101, 102
        // 동생의 주변 1칸은 +2시간에 이동 가능
    }

    public static int bfsN(int[] ks, int N, int K) {
        boolean[][] visited = new boolean[500001][2]; // 0~500000
//        Arrays.fill(visited, 999999);

        if(N == K) return 0;

//        System.out.println(ks[32] + ", " + ks[33] + ", " + ks[34] + ", " + ks[35] + ", " + ks[36] + ", ");
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));

        int min = 999999;
        int minDepth = 999999;
        while(!queue.isEmpty()) {
            Point current = queue.poll();

//            if(current.x == 31) {
//                System.out.println(current.depth);
//            }

            // 15 23
            // 30 24
            // 30 26
            // 29 29
            if(visited[current.x][(current.depth + 1) % 2] && ks[current.x] > 0) {
                System.out.println(ks[current.x]);
                min = Math.min(min, ks[current.x]);
            }
            if(current.x <= 100)
                System.out.println(current.x + ", " + current.depth + ", " + ks[current.x] + ", " + visited[current.x]);
//            if(ks[current.x] > 0 && ks[current.x] - current.depth >= 0 && (ks[current.x] - current.depth) % 2 == 0 && current.depth < minDepth) {
            if(ks[current.x] > 0 && ks[current.x] == current.depth) {
//                return current.depth;
                // 52
                // 7 -> 14 -> 13 -> 26 -> 52 ->
                // 18 -> 19 -> 38 -> 76 -> 68 -> 67 -> 66
                // 66 + 10 = 76

                // 18 -> 36 -> 37 -> 38 -> 76
                // 66 + 10 = 76
                min = Math.min(min, ks[current.x]);
                minDepth = current.depth;
            } // 1, 3, 6, 10

            int left = current.x - 1;
            int right = current.x + 1;
            int teleport = 2 * current.x;

            if(teleport <= 500000 && !visited[teleport][current.depth % 2]) {
                visited[teleport][current.depth % 2] = true;
                queue.add(new Point(teleport, current.depth + 1));
            }

            if(left >= 0 && !visited[left][current.depth % 2]) {
                visited[left][current.depth % 2] = true;
                queue.add(new Point(left, current.depth + 1));
            }
            if(right <= 500000 && !visited[right][current.depth % 2]) {
                visited[right][current.depth % 2] = true;
                queue.add(new Point(right, current.depth + 1));
            } // 1 3 6 10 15 21 28 36
        }

//        return -1;
        return min == 999999 ? -1 : min;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

//        System.out.println(calcSpeed(1, 1));
//        System.out.println(calcSpeed(1, 2));
//        System.out.println(calcSpeed(1, 3));
//        System.out.println(calcSpeed(1, 4));

        int N = scan.nextInt();
        int K = scan.nextInt();

        boolean[][] visited = new boolean[2][500001];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visited[0][N] = true;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            int depth = current.depth;

            int brotherPos = K + depth * (depth + 1) / 2;

            if(brotherPos > 500000) {
                System.out.println(-1); break;
            }

            if(visited[depth % 2][brotherPos]) {
                System.out.println(depth); break;
            }

            for(int next : Arrays.asList(current.x - 1, current.x + 1, current.x * 2)) {
                if(next < 0 || next > 500000) continue;

                if(!visited[(depth + 1) % 2][next]) {
                    visited[(depth + 1) % 2][next] = true;
                    queue.add(new Point(next, depth + 1));
                }
            }
        }
    }
}
