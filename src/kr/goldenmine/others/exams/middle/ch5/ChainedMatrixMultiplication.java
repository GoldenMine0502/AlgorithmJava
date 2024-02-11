package kr.goldenmine.others.exams.middle.ch5;

import java.util.LinkedList;
import java.util.Queue;

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
        /*
        𝐴!: 10x20, 𝐴": 20x5, 𝐴#: 5x15, 𝐴$: 15x30

        class Point3D(i, k, j)
        class Point(i, j)

        // Point3D 2D Array, 이전 정보 저장하기
        P[n + 1][n + 1]

        for i = 1 to n
            C[i, i] = 0
        for L = 1 to n - 1
            for i = 1 to n - L
                j = i + L
                C[i, j] = INF
                for k = i to j - 1
                    temp = C[i, k] + C[k + 1, j] + d_(i-1) * d_k * d_j

                    if(temp < C[i, j])
                        C[i, j] = temp
                        P[i, j] = Point3D(i, k, j)

        // Point Array, 이진 트리 구성을 위해 여유로운 공간 생성(한번 분화에 2배씩 증가하기 때문).
        routes[2^(N+2)]
        index = 0

        // 이진트리를 만들기 위한 Point3D 큐
        queue = []

        routes[index++] = null

        queue.add(previous[1][n])
        routes[index++] = Point(1, n)

        while queue is not empty
            last = queue.poll()

            // 자식 노드가 없으므로 null 넣고 진행
            if(last == null)
                routes[index++] = null;
                routes[index++] = null;
                continue

            lastlastLeft = previous[last.i][last.k]
            lastlastRight = previous[last.k + 1][last.j];

            routes[index++] = Point(last.i, last.k)
            queue.add(lastlastLeft)

            routes[index++] = Point(last.k + 1, last.j)
            queue.add(lastlastRight)

        이진 트리의 가장 depth가 깊은 곳부터 출력
        //return C[1, n]
         */
        Matrix[] matrices = {
                new Matrix(10, 20),
                new Matrix(20, 5),
                new Matrix(5, 15),
                new Matrix(15, 30),
                new Matrix(30, 10),
        };

        final int INF = 9999999;
        final int n = matrices.length;

        int[][] dp = new int[n + 1][n + 1];
        Point3D[][] previous = new Point3D[n + 1][n + 1];

        // Input: A_1 * A_2 * ⋯ * A_n, A_1 shape is d_0xd_1, A_2 shape is d_1xd_2, ⋯, A_n shape is d_n-1 * d_n

        int[] d = new int[n + 1];
        d[0] = matrices[0].y;
        for(int i = 1; i <= n; i++) {
            d[i] = matrices[i - 1].x;
        }

        // for L=1 to n-1
        for (int L = 1; L <= n - 1; L++) {
            // for i = 1 to n-L
//            System.out.println(" === L = " + L + " ===");
            for (int i = 1; i <= n - L; i++) {
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
                    if(cost < dp[i][j]) {
                        dp[i][j] = cost;
                        previous[i][j] = new Point3D(i, k, j); // k를 통해 과거 추적
                        // 이제 다음엔 dp[i][k]와 dp[k + 1][j]에서 찾으면 된다.
                    }

//                    System.out.println("L=" + L + ", i=" + i + ", j=" + j);
//                    System.out.println("L=" + L + ", i=" + i + ", k=" + k + ", j=" + j + " -> " + cost + " = " + dp[i][k] + " + " + dp[k + 1][j] + " + " + d[i - 1] * d[k] * d[j]);
                }
            }
        }

        // 한번 최단경로를 찾을 떄마다 두개씩 분기되므로 최소 2^n개 정도는 필요하다...
        // 이 배열은 이진 트리일 것
        Point[] lasts = new Point[(int) Math.pow(2, n + 2)];
        int index = 0;

        Queue<Point3D> queue = new LinkedList<>();

        // 첫 인덱스 비우기
        lasts[index++] = null;

        queue.add(previous[1][n]);
        lasts[index++] = new Point(1, n);

        while(!queue.isEmpty()) {
            Point3D last = queue.poll();

            if(last == null) { // 자식 노드가 없으므로 null 넣고 진행
                lasts[index++] = null;
                lasts[index++] = null;
                continue;
            }

            Point3D lastlastLeft = previous[last.i][last.k];
            Point3D lastlastRight = previous[last.k + 1][last.j];

//            System.out.println(last + ", " + (lastlastLeft != null) + ", " + (lastlastRight != null));

//            System.out.println("left add: " + lastlastLeft + " from " + last);
            lasts[index++] = new Point(last.i, last.k);
            queue.add(lastlastLeft);

//            System.out.println("right add: " + lastlastRight + " from " + last);
            lasts[index++] = new Point(last.k + 1, last.j);
            queue.add(lastlastRight);
        }

        for(int i = 0; i < index; i++) {
            System.out.println(lasts[i]);
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

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.print(previous[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(dp[1][n]);
    }
}
