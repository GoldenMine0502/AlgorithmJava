package kr.goldenmine.gold.gold1.p2098F;

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

    static int minimum = Integer.MAX_VALUE;

    public static void dfs(int N, int all, int[][] dp, int[][] adjointMatrix, int[] visited, int x) {
        // 4 bytes
        // 00000000 00000000 00000000 00000000
        //

        if(visited[x] == all) {

        }

        for(int i = 1; i <= N; i++) {
            int next = adjointMatrix[x][i];

            if(next > 0) {
                int digit = 1 << i;
                if((visited[x] & digit) == 0) {
                    visited[x] |= digit;
//                    dfs(N, all, dp, )
                }
            }
        }
    }

    public static int getAllVisitedNumber(int N) {
        int sum = 0;

        for(int i = 1; i <= N; i++) {
            int digit = 1 << i;
            sum |= digit;
        }

        return sum;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] adjointMatrix = new int[N + 1][N + 1];
        int[][] dp = new int[N + 1][N + 1];
        int[] visited = new int[N + 1];

        for(int x = 1; x <= N; x++) {
            for(int y = 1; y <= N; y++) {
                adjointMatrix[x][y] = scan.nextInt();
            }
        }

        dfs(N, getAllVisitedNumber(N), dp, adjointMatrix, visited, 1);

        // 무조건 A부터 시작한다고 가정.
        // A에서 B로 가는 경우:
        // A -> B
        // A -> C -> B
        // A -> C -> D -> B
        // A -> D -> B
        // A -> D -> C -> B
        // 
        // A에서


    }
}
