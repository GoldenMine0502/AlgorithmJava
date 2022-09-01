package kr.goldenmine.contest.dandae220813.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        int[] arr = new int[N];
        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            int start = scan.nextInt() - 1;
            int finish = scan.nextInt() - 1;

            nodes.get(start).add(finish);
            nodes.get(finish).add(start);
        }

        int max = 0;
        // 
    }

//    public static List<Integer> bfs(int[] arr, List<List<Integer>> nodes) {
//
//    }
}
