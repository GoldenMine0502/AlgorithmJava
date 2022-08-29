package kr.goldenmine.silver.silver2.p11725;

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

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt();
//        int[] tree = new int[4 * N];

        int[] parents = new int[N + 1];
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            int start = scan.nextInt();
            int finish = scan.nextInt();

            list.get(start).add(finish);
            list.get(finish).add(start);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while(!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> nexts = list.get(current);

            for(int i = 0; i < nexts.size(); i++) {
                int next = nexts.get(i);

                if(parents[next] == 0) {
                    parents[next] = current;
                    queue.add(next);
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 2; i <= N; i++) {
            writer.write(String.valueOf(parents[i]));
            writer.newLine();
        }

        writer.close();
    }
}
