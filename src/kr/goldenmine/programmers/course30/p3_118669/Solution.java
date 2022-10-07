package kr.goldenmine.programmers.course30.p3_118669;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
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

    public static int intensity(int n, List<List<Node>> graph, int[] gates) {
        int[] dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));

        for(int i = 0; i < gates.length; i++) {
            queue.offer(new Node(gates[i], 0));
            dist[gates[i]] = 0;
        }

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

        return 0;
    }

    // 다익 두번
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < paths.length; i++) {
            int u = paths[i][0];
            int v = paths[i][1];
            int w = paths[i][2];

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        int startIntensity = 0;

        return answer;
    }

    public static void main(String[] args) {

    }
}
