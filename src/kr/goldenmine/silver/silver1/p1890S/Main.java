package kr.goldenmine.silver.silver1.p1890S;

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

    // 재귀로 풀었는데 스택이 1000개정도로 제한이라 너무 많으면 풀수가 없음
    public static int solve(int[][] arr, int[][] routes, int N, int y, int x) {

        int value = arr[y][x];

        int count = 0;

        if(y == N - 1 && x == N - 1) return 1;

        if(x + value < N) {
            if(routes[y][x + value] == 0) {
                count += solve(arr, routes, N, y, x + value);
            } else {
                count += routes[y][x + value];
            }
        }
        if(y + value < N) {
            if(routes[y + value][x] == 0) {
                count += solve(arr, routes, N, y + value, x);
            } else {
                count += routes[y + value][x];
            }
        }

        routes[y][x] = count;

        return count;
    }

    public static void solve2(int[][] arr, long[][] routes, int N) {
        for(int y = N - 1; y >= 0; y--) {
            for(int x = N - 1; x >= 0; x--) {
                int value = arr[y][x];

                if(y == N - 1 && x == N - 1) {
                    routes[y][x] = 1; // 만약 인덱스 어느 누군가가 여길 방문해준다면...
                } else {
                    if (x + value < N) { // 1을 더할 기회는 충분하다, 0이 아닌 값을 찾았다면 이 값은 과거에 arr[N - 1][N - 1]을 방문한 적이 있을 것이기 때문
                        routes[y][x] += routes[y][x + value];
                    }

                    if (y + value < N) { // 1을 더할 기회는 충분하다, 0이 아닌 값을 찾았다면 이 값은 과거에 arr[N - 1][N - 1]을 방문한 적이 있을 것이기 때문
                        routes[y][x] += routes[y + value][x];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();

        int N = scan.nextInt();

        int[][] arr = new int[N][N];
        long[][] routes = new long[N][N];

        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N; i++) {
                arr[j][i] = scan.nextInt();
            }
        }

        solve2(arr, routes, N);

        System.out.println(routes[0][0]);
    }
}
