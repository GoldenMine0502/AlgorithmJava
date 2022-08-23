package kr.goldenmine.gold.gold4.p1753S_dijkstra;

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

    static class Node {// 다음 노드의 인덱스와, 그 노드로 가는데 필요한 비용을 담고 있다.
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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int V = scan.nextInt();
        int E = scan.nextInt();
        int start = scan.nextInt();

        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            int w = scan.nextInt();

            graph.get(u).add(new Node(v, w));
//            graph.get(v).add(new Node(u, w));
        }

        int[] dist = new int[V + 1];
        for(int i = 0; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

//            System.out.println(current);

            if (dist[current.idx] < current.cost) {
                continue;
            }

            List<Node> nexts = graph.get(current.idx);

            for(int i = 0; i < nexts.size(); i++) {
                Node next = nexts.get(i);

                int nextCost = current.cost + next.cost;

                if(dist[next.idx] > nextCost) {
                    dist[next.idx] = nextCost;
                    queue.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= V; i++) {
            if(dist[i] != Integer.MAX_VALUE)
                sb.append(dist[i]);
            else
                sb.append("INF");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
