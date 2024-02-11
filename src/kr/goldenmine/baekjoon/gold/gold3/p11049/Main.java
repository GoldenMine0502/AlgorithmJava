package kr.goldenmine.baekjoon.gold.gold3.p11049;

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

    static class Matrix {
        int y;
        int x;

        public Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int multiplyCount(Matrix other) {
            return y * x/*(other.y)*/ * other.x;
        }
    }

    static class Point3D {
        int i;
        int k;
        int j;

        public Point3D(int i, int k, int j) {
            this.i = i;
            this.k = k;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", k=" + k +
                    ", j=" + j +
                    '}';
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        /*
        𝐴!: 10x20, 𝐴": 20x5, 𝐴#: 5x15, 𝐴$: 15x30
        */
        Matrix[] matrices = new Matrix[N];

        for(int i = 0; i < N; i++) {
            matrices[i] = new Matrix(scan.nextInt(), scan.nextInt());
        }

        final int INF = Integer.MAX_VALUE;

        int[][] dp = new int[N + 1][N + 1];
//        Point3D[][] previous = new Point3D[n + 1][n + 1];

        // Input: A_1 * A_2 * ⋯ * A_n, A_1 shape is d_0xd_1, A_2 shape is d_1xd_2, ⋯, A_n shape is d_n-1 * d_n

        int[] d = new int[N + 1];
        d[0] = matrices[0].y;
        for (int i = 1; i <= N; i++) {
            d[i] = matrices[i - 1].x;
        }

        // for L=1 to n-1
        for (int L = 1; L <= N - 1; L++) {
            // for i = 1 to n-L
//            System.out.println(" === L = " + L + " ===");
            for (int i = 1; i <= N - L; i++) {
                // j = i + L
                int j = i + L;

                // C[i, j] = ∞
                dp[i][j] = INF;

//                System.out.println("i=" + i);

                // for k = i to j-1
                for (int k = i; k < j; k++) {
                    // temp = C[i, k] + C[k+1, j] + d_i-1 * d_k * d_j
                    int cost = dp[i][k] + dp[k + 1][j] + d[i - 1] * d[k] * d[j];

                    // if (temp < C[i, j]) C[i, j] = temp
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
//                        previous[i][j] = new Point3D(i, k, j); // k를 통해 과거 추적
                        // 이제 다음엔 dp[i][k]와 dp[k + 1][j]에서 찾으면 된다.
                    }

//                    System.out.println("L=" + L + ", i=" + i + ", j=" + j);
//                    System.out.println("L=" + L + ", i=" + i + ", k=" + k + ", j=" + j + " -> " + cost + " = " + dp[i][k] + " + " + dp[k + 1][j] + " + " + d[i - 1] * d[k] * d[j]);
                }
            }
        }

        System.out.println(dp[1][N]);
    }
}
