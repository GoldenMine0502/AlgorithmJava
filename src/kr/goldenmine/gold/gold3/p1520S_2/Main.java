package kr.goldenmine.gold.gold3.p1520S_2;


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

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(int x, int y) {
            return new Point(this.x + x, this.y + y);
        }
    }

    static int M, N;
    static int[][] arr, dp;
    static int[] rangeX = { -1, 0, 1, 0 };
    static int[] rangeY = { 0, 1, 0, -1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        FastReader scan = new FastReader();

        M = scan.nextInt();
        N = scan.nextInt();

        arr = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = scan.nextInt();
            }
        }

        dp = new int[M + 1][N + 1]; // (x, y)에서 도착점으로 가는 경로의 개수
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(DFS(1, 1));

//        for(int i = 1; i <= M; i++) {
//            for(int j = 1; j <= N; j++) {
//                System.out.print((dp[i][j] == -1 ? " " : dp[i][j]) + " ");
//            }
//            System.out.println();
//        }
    }

    public static int DFS(int x, int y) {
        if (x == M && y == N) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화.
        for (int i = 0; i < 4; i++) {
            int dx = x + rangeX[i];
            int dy = y + rangeY[i];

            if (dx < 1 || dy < 1 || dx > M || dy > N) {
                continue;
            }
//            System.out.println(dx + ", " + dy);

            // arr[x][y]보다 arr[dx][dy]가 높이가 더 낮다면
            // arr[dx][dy]에서 끝점까지 도달하는 경로의 개수를 더한다.
            if (arr[x][y] > arr[dx][dy]) {
//                System.out.println(dp[x][y] + ", " + dx + ", " + dy);
                dp[x][y] += DFS(dx, dy);
//                System.out.println(dp[x][y] + ", " + dx + ", " + dy);
            }
        }

        return dp[x][y];
    }


//    public static void main(String[] args) {
//        FastReader scan = new FastReader();
//
//        int Y = scan.nextInt();
//        int X = scan.nextInt();
//
//        int[][] arr = new int[Y][X];
//
//        for(int i = 0; i < Y; i++) {
//            for(int j = 0; j < X; j++) {
//                arr[i][j] = scan.nextInt();
//            }
//        }
//
//        int[][] cache = new int[Y + 1][X + 1];
//
//        for(int y = 1; y <= Y; y++) {
//            for (int x = 1; x <= X; x++) {
//
//            }
//        }
//
//        System.out.println(cache[Y - 1][X - 1]);
//    }
}
