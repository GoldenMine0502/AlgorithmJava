package kr.goldenmine.baekjoon.silver.silver1.p1389S;

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
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int K = scan.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < K; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            nodes.get(start).add(finish);
            nodes.get(finish).add(start);
        }

        Queue<Point> queue = new LinkedList<>();

        int[] arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            queue.add(new Point(i, 1));
            int value = 0;

            while(!queue.isEmpty()) {
                Point node = queue.poll();

                value += node.y;

                List<Integer> nexts = nodes.get(node.x);

                for(int j = 0; j < nexts.size(); j++) {
                    int next = nexts.get(j);

                    if(!visited[next]) {
                        visited[next] = true;
                        queue.add(new Point(next, node.y + 1));
                    }
                }
            }
            arr[i] = value;
        }

//        System.out.println(Arrays.toString(arr));

        int min = 999999;
        int index = -1;
        for(int i = 1; i <= N; i++) {
            if(min > arr[i]) {
                min = Math.min(min, arr[i]);
                index = i;
            }
        }

        System.out.println(index);
    }
}
