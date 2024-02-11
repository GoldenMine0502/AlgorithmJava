package kr.goldenmine.baekjoon.gold.gold3.p11779;

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

    static class Cost {
        int cost;
        int lastIndex;

        public Cost(int min, int lastIndex) {
            this.cost = min;
            this.lastIndex = lastIndex;
        }

        @Override
        public String toString() {
            return "Cost{" +
                    "cost=" + cost +
                    ", lastIndex=" + lastIndex +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int V = scan.nextInt();
        int E = scan.nextInt();

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

        int start = scan.nextInt();
        int finish = scan.nextInt();

        Cost[] dist = new Cost[V + 1];
        for(int i = 0; i <= V; i++) {
            dist[i] = new Cost(Integer.MAX_VALUE, -1);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));
        queue.offer(new Node(start, 0));
        dist[start] = new Cost(0, start);

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if (dist[current.idx].cost < current.cost) {
                continue;
            }

            List<Node> nexts = graph.get(current.idx);

            for(int i = 0; i < nexts.size(); i++) {
                Node next = nexts.get(i);

                int nextCost = current.cost + next.cost;

                if(dist[next.idx].cost > nextCost) {
                    dist[next.idx] = new Cost(nextCost, current.idx);
                    queue.add(new Node(next.idx, dist[next.idx].cost));
                }
            }
        }

        System.out.println(dist[finish].cost);

        StringBuilder sb = new StringBuilder();

        List<Cost> lasts = new ArrayList<>();
        Cost last = dist[finish];

        while(last.lastIndex != start) {
            lasts.add(last);
            last = dist[last.lastIndex];
        }

        lasts.add(last);


        for(int i = lasts.size() - 1; i >= 0; i--) {
            sb.append(lasts.get(i).lastIndex);
            sb.append(" ");
        }

        sb.append(finish);
        sb.append(" ");
//        for(int i = 1; i <= V; i++) {
//            if(dist[i].cost != Integer.MAX_VALUE)
//                sb.append(dist[i]);
//            else
//                sb.append("INF");
//            sb.append(" ");
//        }
        System.out.println(lasts.size() + 1);
        System.out.println(sb);
    }
}
