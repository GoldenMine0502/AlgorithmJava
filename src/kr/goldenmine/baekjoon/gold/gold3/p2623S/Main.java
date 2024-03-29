package kr.goldenmine.baekjoon.gold.gold3.p2623S;

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

        int N = scan.nextInt();
        int M = scan.nextInt();

        int[] indegree = new int[N + 1];
        List<List<Integer>> nodes = new ArrayList<>();

        for (int i = 0; i <= N; i++)
            nodes.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            int K = scan.nextInt();

            int last = -1;

            for(int j = 0; j < K; j++) {
                int current = scan.nextInt();

                if(last != -1) {
                    nodes.get(last).add(current);
                    indegree[current]++;
                }

                last = current;
            }
        }

        List<Integer> result = topologicalSort(N, indegree, nodes);

        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
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
        for (int i=1;i<=N;i++) {
            // 사이클 발생 IMPOSSIBLE
            if (q.isEmpty()) return new ArrayList<>(Arrays.asList(0));
            // 이동할 수 있는 정점이 여러 개 == 나올 수 있는 최종 순위가 여러 개
//            else if (q.size()>1) return new ArrayList<>(Arrays.asList(0));
            int node = q.poll();
            result.add(node);

            for (Integer j : array.get(node)) {
                indegree[j]--;

                if (indegree[j] == 0) {
                    q.offer(j);
                }
            }
        }

        return result;
    }
}
