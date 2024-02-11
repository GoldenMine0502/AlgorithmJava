package kr.goldenmine.others.exams.middle.ch5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {
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
//        int count;
//        int max;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
//            this.count = count;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
//                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
//        Scanner scan;
        FastReader scan = new FastReader();

        /*
        28.
            class Point(x, y)

            // previous (x, y) points, initializes all to null
            P[N + 1][K + 1] = [ null, ]

            for i = 0 to n
                K[i, 0] = 0

            for w = 0 to C
                K[0, w] = 0

            for i = 1 to n
                for w = 1 to C
                    if(w_i > w)
                        K[i, w] = K[i - 1, w]
                        P[i, w] = (i - 1, w)
                    else
                        if(K[i - 1, w] < K[i - 1, w - w_i] + v_i)
                            K[i, w] = K[i - 1, w - w_i] + v_i
                            P[i, w] = Point(i - 1, w - w_i)
                        else
                            K[i, w] = K[i - 1, w]
                            P[i, w] = Point(i - 1, w)

            // number array
            routes[K]

            index = 0
            last = Point(n, C)

            while last != null
                lastlast = P[last.x, last.y]
                lastlastY = lastlast != null ? lastlast.y : 0

                if(lastlastY < last.y)
                    if(last.x > 0)
                        routes[index++] = last.x;

                last = lastlast

            // 배열의 인덱스 뒤집어서 리턴
            // 인덱스는 배낭에 담길 수 있는 아이템의 인덱스에 해당함. 1부터 시작.
            return routes.reverse()
         */

        /*
                while (last != null) {
            Point lastlast = weightsUsed[last.x][last.y];

            if ((lastlast != null ? lastlast.y : 0) < last.y) {
                if (last.x > 0)
                    lasts[index++] = last.x;
            }
            last = lastlast;
        }
         */

        int N = scan.nextInt();
        int K = scan.nextInt();

        int[] weights = new int[N + 1];
        int[] values = new int[N + 1];
        int[][] dp = new int[N + 1][K + 1];
        Point[][] weightsUsed = new Point[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            weights[i] = scan.nextInt();
            values[i] = scan.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (weights[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                    weightsUsed[i][j] = new Point(i - 1, j);
                } else { // j >= weight

                    if (dp[i - 1][j] < dp[i - 1][j - weights[i]] + values[i]) {
                        dp[i][j] = dp[i - 1][j - weights[i]] + values[i];
                        weightsUsed[i][j] = new Point(i - 1, j - weights[i]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        weightsUsed[i][j] = new Point(i - 1, j);
                    }
                }
            }
        }

        int[] lasts = new int[K];
        int index = 0;
        Point last = new Point(N, K);

        while (last != null) {
            Point lastlast = weightsUsed[last.x][last.y];

            if ((lastlast != null ? lastlast.y : 0) < last.y) {
                if (last.x > 0)
                    lasts[index++] = last.x;
            }
            last = lastlast;
        }
        for (int i = index - 1; i >= 0; i--) {
            System.out.print(lasts[i] + " ");
        }
        System.out.println();

//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j <= K; j++) {
//                System.out.print(weightsUsed[i][j] + " ");
//            }
//            System.out.println();
//        }

//        System.out.println(weightsUsed[N][K]);

        System.out.println(dp[N][K]);
    }
}
