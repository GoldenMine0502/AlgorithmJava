package kr.goldenmine.gold.gold1.p3665F;

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

    /*
    5 4 3 2 1
    2 4
    5 2 3 4 1
    3 4
    5 2 4 3 1

     */

    static class Data {
        int rank;
        int data;

        public Data(int rank, int data) {
            this.rank = rank;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "rank=" + rank +
                    ", data=" + data +
                    '}';
        }
    }

    static List<Data> topologicalSort(int N, Data[] ranks, int[] indegree, List<List<Integer>> array) {
        List<Data> result = new ArrayList<>();

        Queue<Data> q = new LinkedList<>();

        // 큐에 indegree 가 0 인 노드 담기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(ranks[i]);
            }
        }

        for(int i = 1; i <= N; i++) {
            if(q.isEmpty()) {
                throw new RuntimeException("사이클 발생");
            }

            Data data = q.poll();
            result.add(data);

            System.out.println(data);

            List<Integer> nodes = array.get(data.rank);

            for(int j = 0; j < nodes.size(); j++) {
                int y = nodes.get(j);

                System.out.println(y);

                if(--indegree[y] == 0) {
                    q.add(ranks[y]);
                }
            }
        }

        return result;
    }

    static FastReader scan = new FastReader();

    public static void solve() {
        int N = scan.nextInt();

        Data[] arr = new Data[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = new Data(i, scan.nextInt());
        }

        int M = scan.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();
        int[] indegree = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            nodes.get(start).add(finish);
            indegree[finish]++;
        }

        List<Data> result = topologicalSort(N, arr, indegree, nodes);

        System.out.println(result);
    }

    public void swap(int n1, int n2) {

    }

    public static void main(String[] args) {
        int T = scan.nextInt();

        for(int i = 0; i < T; i++) {
            solve();
        }
    }
}
