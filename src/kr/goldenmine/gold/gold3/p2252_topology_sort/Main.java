package kr.goldenmine.gold.gold3.p2252_topology_sort;

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

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt(); // 정점 갯수
        int E = scan.nextInt(); // 간선 갯수

        int[] indegree = new int[N + 1];
        List<List<Integer>> array = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            array.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            int c1 = scan.nextInt();
            int c2 = scan.nextInt();

            array.get(c1).add(c2);
            indegree[c2]++;
        }

        List<Integer> result = topologicalSort(N, indegree, array);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            sb.append(" ");
        }

        System.out.println(sb);
    }

    static List<Integer> topologicalSort(int N, int[] indegree, List<List<Integer>> array) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // 큐에 indegree 가 0 인 노드 담기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        /**
         * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree 를 1 감소
         * 2. 만약 indegree가 0 이 된다면 큐에 넣기
         * 3. 큐가 빌때까지 반복
         */
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);

            for (Integer i : array.get(node)) {
                indegree[i]--;

                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        return result;
    }
}
