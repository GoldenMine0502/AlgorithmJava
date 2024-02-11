package kr.goldenmine.baekjoon.silver.silver2.p1535S;

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

    static int max = 0;

    static boolean[] totalVisited = new boolean[1 << 20];

    public static void dfs(int N, int[][] arr, int visited, int n, int hp, int gain) {
        max = Math.max(max, gain);
        if(N == n) {
            return;
        }
        for(int i = 0; i < N; i++) {
            int bit = 1 << i;
            if((visited & bit) == 0) {
                visited |= bit;
                if (!totalVisited[visited]) {
                    totalVisited[visited] = true;
                    if (hp - arr[0][i] > 0) {
                        dfs(N, arr, visited, n + 1, hp - arr[0][i], gain + arr[1][i]);
                    }
                }
                visited &= ~bit;
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        // Read
        int N = scan.nextInt();

        int[][] arr = new int[2][N];
        for(int i = 0; i < N; i++) {
            arr[0][i] = scan.nextInt();
        }
        for(int i = 0; i < N; i++) {
            arr[1][i] = scan.nextInt();
        }
        // Solve
//        boolean[] visited = new boolean[N];
        dfs(N, arr, 0, 0, 100, 0); // 기본 체력은 100, 얻는 기쁨은 0
        System.out.println(max);


    }
}
