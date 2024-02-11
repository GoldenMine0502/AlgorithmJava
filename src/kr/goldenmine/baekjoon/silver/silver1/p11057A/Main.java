package kr.goldenmine.baekjoon.silver.silver1.p11057A;

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
        int[][] cache = new int[N + 1][10];

        cache[0][0] = 1;

        cache[1][0] = 1;
        cache[1][1] = 1;
        cache[1][2] = 1;
        cache[1][3] = 1;
        cache[1][4] = 1;
        cache[1][5] = 1;
        cache[1][6] = 1;
        cache[1][7] = 1;
        cache[1][8] = 1;
        cache[1][9] = 1;

//        cache[2][0] = 1;
//        cache[2][1] = 2;
//        cache[2][2] = 3;
//        cache[2][3] = 4;
//        cache[2][4] = 5;
//        cache[2][5] = 6;
//        cache[2][6] = 7;
//        cache[2][7] = 8;
//        cache[2][8] = 9;
//        cache[2][9] = 10;

        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    cache[i][j] += cache[i - 1][k];
                    cache[i][j] %= 10007;
                }
            }
        }


        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += cache[N][i];
        }

        System.out.println(sum % 10007);
    } // 이전 * 45 + 10
}
