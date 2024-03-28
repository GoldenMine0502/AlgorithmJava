package kr.goldenmine.baekjoon.platinum.platinum5.p10875;

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

    static class Info {
        int move;
        String direction;

        public Info(int move, String direction) {
            this.move = move;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int L = scan.nextInt();
        int N = scan.nextInt();

        List<Info> nexts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int move = scan.nextInt();
            String direction = scan.next();

            nexts.add(new Info(move, direction));
        }
    }
}
