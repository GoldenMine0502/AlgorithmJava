package kr.goldenmine.others.exams.huffman;

import java.util.*;

public class Dijkstra {
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

        int V = 5;
        int E = 10;
        int start = 1;

        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        int[][] arr = {
                {1, 4, 5},
                {1, 2, 10},
                {2, 4, 2},
                {2, 3, 1},
                {3, 5, 4},
                {4, 2, 3},
                {4, 3, 9},
                {4, 5, 2},
                {5, 3, 6},
                {5, 1, 7}
        };

        for(int i = 0; i < E; i++) {
            int u = arr[i][0];
            int v = arr[i][1];
            int w = arr[i][2];

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

            System.out.println(current + ", " + Arrays.toString(dist));

            if (dist[current.idx] < current.cost) {
                continue;
            }

            List<Node> nexts = graph.get(current.idx);

            for(int i = 0; i < nexts.size(); i++) {
                Node next = nexts.get(i);


                int nextCost = current.cost + next.cost;

                if(dist[next.idx] > nextCost) {
                    System.out.println("next: " + next);
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
