package kr.goldenmine.exams.middle.ch5;

public class ChainedMatrixMultiplication {
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
    public static void main(String[] args) {
        /*
        𝐴!: 10x20, 𝐴": 20x5, 𝐴#: 5x15, 𝐴$: 15x30
         */
        Matrix[] matrices = {
                new Matrix(10, 20),
                new Matrix(20, 5),
                new Matrix(5, 15),
                new Matrix(15, 30),
//                new Matrix(30, 10),
        };

        final int INF = 9999999;
        final int n = matrices.length;

        int[][] dp = new int[n + 1][n + 1];

        // Input: A_1 * A_2 * ⋯ * A_n, A_1 shape is d_0xd_1, A_2 shape is d_1xd_2, ⋯, A_n shape is d_n-1 * d_n

        int[] d = new int[n + 1];
        d[0] = matrices[0].y;
        for(int i = 1; i <= n; i++) {
            d[i] = matrices[i - 1].x;
        }

        // for L=1 to n-1
        for (int L = 1; L <= n - 1; L++) {
            // for i = 1 to n-L
            System.out.println(" === L = " + L + " ===");
            for (int i = 1; i <= n - L; i++) {
                // j = i + L
                int j = i + L;

                // C[i, j] = ∞
                dp[i][j] = INF;

                System.out.println("i=" + i);

                // for k = i to j-1
                for (int k = i; k < j; k++) {
                    // temp = C[i, k] + C[k+1, j] + d_i-1 * d_k * d_j
                    int cost = dp[i][k] + dp[k + 1][j] + d[i - 1] * d[k] * d[j];

                    // if (temp < C[i, j]) C[i, j] = temp
                    dp[i][j] = Math.min(dp[i][j], cost);

                    System.out.println("i=" + i + ", k=" + k + ", j=" + j + " -> " + cost + " = " + dp[i][k] + " + " + dp[k + 1][j] + " + " + d[i - 1] * d[k] * d[j]);
                }
            }
        }

        System.out.print("\t");
        for(int i = 1; i <= n; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for(int i = 1; i <= n; i++) {
            System.out.print("\t" + i + ">>");
            for(int j = 1; j <= n; j++) {
                System.out.print("\t" + dp[i][j]);
            }
            System.out.println();
        }

        System.out.println(dp[1][n]);
    }
}
