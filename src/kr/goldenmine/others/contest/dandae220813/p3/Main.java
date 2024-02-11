package kr.goldenmine.others.contest.dandae220813.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String text = scan.next();

        int[][] dp = new int[N][4];
        // D가 나오면 계속 D count 증가
        // K가 나오면 D count는 0으로, K 카운트 1 증가
        // S가 나오면 K count는 0으로,

        // D A B K C D S E F H H
        // 1 1 1 1 1 1 1 1 1 1 1
        // 0 0 0 1 1 1 1 1 1 1 1
        // 0 0 0 0 0 0 1 1 1 1 1
        // 0 0 0 0 0 0 0 0 0 1 2

        // D A B K K D S E F H H
        // 1 1 1 1 1 1 1 1 1 1 1
        // 0 0 0 1 2 2 2 2 2 2 2
        // 0 0 0 0 0 0 2 2 2 2 2
        // 0 0 0 0 0 0 0 0 0 2 4

        // D K D A K A S H
        // 1 1 2 2 2 2 2 2
        // 0 1 1 1 3 3 3 3
        // 0 0 0 0 0 0 3 3
        // 0 0 0 0 0 0 0 3

        // D K D S K S S H
        // 1 0 1 1 1
        // 0 1 1 1 2
        // 0 0 0 1 2
        // 0 0 0 0 0

        // D K D K D K
        // 1 1 2 2 3 3
        // 0 1 1 3 3 6

        // D K D K D K S H
        // 1 1 2 2 3 3 3 3
        // 0 1 1 3 3 6 6 6
        // 0 0 0 0 0 0 6 6
        // 0 0 0 0 0 0 0 6

        // D K D K D K S H H
        // 1 1 2 2 3 3 3 3 3
        // 0 1 1 3 3 6 6 6 6
        // 0 0 0 0 0 0 6 6 6
        // 0 0 0 0 0 0 0 6 12
    }
}
