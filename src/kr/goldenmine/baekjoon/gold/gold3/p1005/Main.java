package kr.goldenmine.baekjoon.gold.gold3.p1005;

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

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();
            int K = scan.nextInt();

            int[] arr = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                arr[i] = scan.nextInt();
            }

            int[] indegree = new int[N + 1];
            int[] minimumTimes = new int[N + 1];

            List<List<Integer>> nodes = new ArrayList<>();

            for (int i = 0; i <= N; i++)
                nodes.add(new ArrayList<>());

            for (int i = 0; i < K; i++) {
                int start = scan.nextInt();
                int finish = scan.nextInt();

                nodes.get(start).add(finish);
                indegree[finish]++;
            }

            for(int i = 1; i <= N; i++) {
                minimumTimes[i] = 0;
            }

            topologicalSort(N, indegree, arr, minimumTimes, nodes);

            int result = scan.nextInt();
            System.out.println(minimumTimes[result]);
//            System.out.println("minimum: " + Arrays.toString(minimumTimes) + ", " + minimumTimes[result]);
        }
    }


    static void topologicalSort(int N, int[] indegree, int[] times, int[] minimumTimes, List<List<Integer>> array) {
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> result = new LinkedList<Integer>();

        // 큐에 indegree 가 0 인 노드 담기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                minimumTimes[i] = times[i];
            }
        }

        /**
         * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree 를 1 감소
         * 2. 만약 indegree가 0 이 된다면 큐에 넣기
         * 3. 큐가 빌때까지 반복
         */
        while (!q.isEmpty()) {
            int node = q.poll();
            result.offer(node);

//            System.out.println(node);

            for (int i : array.get(node)) {
                indegree[i]--;

                if(indegree[i] >= 0) {
//                    minimumTimes[i] = Math.min(minimumTimes[i], minimumTimes[node] + times[i]);
//                    minimumTimes[i] += minimumTimes[node];
                    minimumTimes[i] = Math.max(minimumTimes[i], minimumTimes[node] + times[i]);
//                    System.out.println("update: " + node + "  -> " + i + ", " + minimumTimes[i]);
                }

                if (indegree[i] == 0) {
                    q.offer(i);
//                    minimumTimes[i] += times[i];
//                    System.out.println("add: " + node + " -> " + i + ", " + minimumTimes[i]);
                }

            }
        }

//        System.out.println("result: " + result);
    }
}
