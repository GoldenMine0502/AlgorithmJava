package kr.goldenmine.baekjoon.gold.gold3.p1516S;

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

    static int N;
    static int e;

    static void topologicalSort(int[] indegree, int[] costs, List<List<Integer>> array) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<Integer>();


        int[] total = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            total[i] = 0;
        }

        // 큐에 indegree 가 0 인 노드 담기
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                total[i] = costs[i];
            }
        }

//        System.out.println(Arrays.toString(total));

        /**
         * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree 를 1 감소
         * 2. 만약 indegree가 0 이 된다면 큐에 넣기
         * 3. 큐가 빌때까지 반복
         */
        while (!q.isEmpty()) {
            int node = q.poll();
            result.offer(node);

            for (Integer i : array.get(node)) {
                indegree[i]--;

                if (indegree[i] == 0) {
                    q.offer(i);
//                    System.out.println(i + ", " + total[i] + ", " + total[node] + ", " + costs[i]);
//                    System.out.println(Arrays.toString(total));
                }
//                System.out.println(node + ", " + i);
                total[i] = Math.max(total[i], total[node] + costs[i]);
            }
        }
        for(int i = 1; i <= N; i++) {
            System.out.println(total[i]);
        }
//        System.out.println(Arrays.toString(total));

//        System.out.println(result);
    }

    static class Point {
        int from;
        int to;
        int cost;

        Point(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        N = scan.nextInt(); // 정점 갯수
        int[] indegree = new int[N + 1];
        List<List<Integer>> array = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            array.add(new ArrayList<>());

        List<Point> points = new ArrayList<>();

        int[] costs = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            int cost = scan.nextInt();

            int read;
            while(true) {
                read = scan.nextInt();
                if(read == -1) break;

                points.add(new Point(read, i));
            }

            costs[i] = cost;
        }

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);

            array.get(p.from).add(p.to);
            indegree[p.to]++;
        }

        topologicalSort(indegree, costs, array);
    }
}
