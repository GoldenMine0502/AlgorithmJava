package kr.goldenmine.baekjoon.gold.gold2.p1167A;

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

    static class Node { // 다음 노드의 인덱스와, 그 노드로 가는데 필요한 비용을 담고 있다.
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", cost=" + cost +
                    '}';
        }
    }

    static class Depth {
        int idx;
        int depth;

        Depth(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }

    static Depth bfs(int N, int start, List<List<Node>> nodes) {
        boolean[] visited = new boolean[N + 1];

        Queue<Depth> queue = new LinkedList<>();
        queue.add(new Depth(start, 0));
        visited[start] = true;

        Depth max = null;

        while(!queue.isEmpty()) {
            Depth next = queue.poll();

            if(max == null || max.depth < next.depth) {
                max = next;
            }

            List<Node> nexts = nodes.get(next.idx);

            for(Node node : nexts) {
                if(!visited[node.idx]) {
                    visited[node.idx] = true;
                    queue.add(new Depth(node.idx, next.depth + node.cost));
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        List<List<Node>> nodes = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 1; i <= N; i++) {
            int start = scan.nextInt();

            while(true) {
                int finish = scan.nextInt();
                if(finish == -1) break;
                int cost = scan.nextInt();

                nodes.get(start).add(new Node(finish, cost));
            }
        }

        int nextStart = bfs(N, 1, nodes).idx;

        int cost = bfs(N, nextStart, nodes).depth;

        System.out.println(cost);
    }
}
