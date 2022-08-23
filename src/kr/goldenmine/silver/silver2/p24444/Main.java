package kr.goldenmine.silver.silver2.p24444;

import java.io.*;
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

    static int count = 1;
    public static void solve(int[] arr, boolean[] visited, List<List<Integer>> nodes, int R) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(R);
        visited[R] = true;

        while(!queue.isEmpty()) {
            int node = queue.poll();

//            System.out.println(node);

            arr[node] = count++;

            List<Integer> nexts = nodes.get(node);

            for(int i = 0; i < nexts.size(); i++) {
                int next = nexts.get(i);

                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
        int M = scan.nextInt();
        int R = scan.nextInt();

        int[] arr = new int[N + 1];

        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            nodes.get(start).add(finish);
            nodes.get(finish).add(start);
        }

        for(int i = 0; i <= N; i++) {
            nodes.get(i).sort(Integer::compareTo);
//            nodes.get(i).sort(Comparator.reverseOrder());
        }

        boolean[] visited = new boolean[N + 1];

        solve(arr, visited, nodes, R);

        BufferedWriter output = new BufferedWriter(
                new OutputStreamWriter(System.out));

        for(int i = 1; i <= N; i++) {
            output.write(String.valueOf(arr[i]));
            output.newLine();
        }

        output.flush();
    }
}
