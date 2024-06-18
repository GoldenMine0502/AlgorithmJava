package kr.goldenmine.baekjoon.gold.gold4.p2140S;

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

    static boolean check(int N, int X, int Y, int[][] map) {
        int count = 0;
        for (int x = X - 1; x <= X + 1; x++) {
            for (int y = Y - 1; y <= Y + 1; y++) {
                if (x == X && y == Y) continue;
                if (x < 0 || x >= N || y < 0 || y >= N) continue;

                if (map[y][x] - '0' > 0 || map[y][x] == '#' || map[y][x] == '*') {
                    count++;
                }
            }
        }


        if (count == 8) {
            for (int x = X - 1; x <= X + 1; x++) {
                for (int y = Y - 1; y <= Y + 1; y++) {
                    if (x < 0 || x >= N || y < 0 || y >= N) continue;

                    if (x == X && y == Y) {
                        map[y][x] = '*';
                    } else if ('0' < map[y][x] && map[y][x] <= '9') {
                        map[y][x]--;
                    }
                }
            }
        }

//        printState(N, map);
        return count == 8;
    }

    static void printState(int N, int[][] map) {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print((char) map[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static int solve(int N, int[][] map) {
        // (y, x) = (1, 1) -> (1, N - 1) -> (N - 1, N - 1) -> (N - 1, 1) -> (2, 1)
        int Y = 1;
        int X = 1;

        check(N, X, Y, map);

        for (; X < N - 2; X++) {
            check(N, X, Y, map);
        } // (1, N - 1)
        for (; Y < N - 2; Y++) {
            check(N, X, Y, map);
        } // (N - 1, N - 1)
        for (; X > 1; X--) {
            check(N, X, Y, map);
        } // (N - 1, 1)
        for (; Y > 1; Y--) {
            check(N, X, Y, map);
        }

        for (int x = 2; x < N - 2; x++) {
            for (int y = 2; y < N - 2; y++) {
                map[y][x] = '*'; // 걍 다 지뢰있다 생각해~
            }
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[j][i] == '*') {
                    total++;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        FastReader scan = new FastReader();
//        int T = scan.nextInt();

//        while (T-- > 0) {
            int N = scan.nextInt();
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = scan.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            System.out.println(solve(N, map));
//        }
    }
}
