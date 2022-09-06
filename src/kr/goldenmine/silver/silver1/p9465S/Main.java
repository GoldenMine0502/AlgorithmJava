package kr.goldenmine.silver.silver1.p9465S;

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

    static FastReader scan = new FastReader();

    public static void solve() {
        int N = scan.nextInt();
        int[][] arr = new int[2][N];
        int[][] cache = new int[2][N];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        for(int i = 0; i < N; i++) {
            cache[0][i] = arr[0][i] + Math.max(Math.max(
                            i >= 2 ? cache[0][i - 2] : 0,
                            i >= 1 ? cache[1][i - 1] : 0),
                    i >= 2 ? cache[1][i - 2] : 0);

            cache[1][i] = arr[1][i] + Math.max(Math.max(
                            i >= 2 ? cache[1][i - 2] : 0,
                            i >= 1 ? cache[0][i - 1] : 0),
                    i >= 2 ? cache[0][i - 2] : 0);
        }

//        System.out.println();
//
//        for(int i = 0; i < 2; i++) {
//            for(int j = 0; j < N; j++) {
//                System.out.print(cache[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(Math.max(cache[0][N - 1], cache[1][N - 1]));
    }

    public static void main(String[] args) {
        int T = scan.nextInt();

        for(int i = 0 ; i < T; i++) {
            solve();
        }
    }
}
