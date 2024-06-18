package kr.goldenmine.baekjoon.silver.silver2.p20438;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

    public static void main(String[] args) throws IOException {
        FastReader scan = new FastReader();

        int N = scan.nextInt() + 2;
        int K = scan.nextInt();
        int Q = scan.nextInt();
        int M = scan.nextInt();

        Set<Integer> Ks = new HashSet<>();
        for(int i = 0; i < K; i++) {
            Ks.add(scan.nextInt());
        }

        Set<Integer> Qs = new HashSet<>();
        for(int i = 0; i < Q; i++) {
            Qs.add(scan.nextInt());
        }
        Qs.removeAll(Ks);

        int[] Ns = new int[N + 1];

//        System.out.println(Qs);

        for(int q : Qs) {
            for(int qq = q; qq <= N; qq += q) {
                Ns[qq] = 1;
            }
        }

        for(int k : Ks) {
            Ns[k] = 0;
        }

        int[] sums = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sums[i] = sums[i - 1] + Ns[i];
        }

//        System.out.println(Arrays.toString(Ns));
//        System.out.println(Arrays.toString(sums));

        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < M; i++) {
            int s = scan.nextInt();
            int f = scan.nextInt();

            w.write(String.valueOf((f - s + 1) - (sums[f] - sums[s - 1])));
            w.newLine();
        }
        w.flush();
    }
}
