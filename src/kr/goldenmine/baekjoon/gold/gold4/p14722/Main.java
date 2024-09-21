package kr.goldenmine.baekjoon.gold.gold4.p14722;

import java.util.Arrays;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
            return (64 < c && c < 91) || (96 < c && c < 123);
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0) buffer[0] = -1;
            }
            return buffer[index++];
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int[][] arr = new int[N + 1][N + 1];


        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                arr[y][x] = scan.nextInt();
            }
        }



        int[][][] dp = new int[3][N + 1][N + 1];
        for(int y = 0; y <= N; y++) {
            for(int x = 0; x <= N; x++) {
                dp[0][y][x] = -1;
                dp[1][y][x] = -1;
                dp[2][y][x] = -1;
            }
        }
        dp[2][0][1] = 0;
        // last[y][x][위 = 0, 왼쪽 = 1]

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int current = 0; current < 3; current++) {
//                    int next = (current + 1) % 3;
                    int last = (current - 1 + 3) % 3;
                    int lastValue = Math.max(dp[last][y][x - 1], dp[last][y - 1][x]);
                    int currentValue = Math.max(dp[current][y][x - 1], dp[current][y - 1][x]);

                    if(arr[y][x] == current) {
                        if (lastValue == -1) continue;
                        dp[current][y][x] = lastValue + 1;
                    } else {
                        dp[current][y][x] = currentValue;
                    }
                }

//                if(arr[y][x] == 1) {
//                    dp[y][x][1] = Math.max(dp[y][x - 1][0], dp[y - 1][x][0]) + 1;
//                } else {
//                    dp[y][x][1] = Math.max(dp[y][x - 1][0], dp[y - 1][x][0]);
//                }
//
//                if(arr[y][x] == 2) {
//                    dp[y][x][2] = Math.max(dp[y][x - 1][1], dp[y - 1][x][1]) + 1;
//                } else {
//                    dp[y][x][2] = Math.max(dp[y][x - 1][1], dp[y - 1][x][1]);
//                }
            }
        }

//        for(int i = 0; i < 3; i++) {
//            for(int y = 0; y <= N; y++) {
//                System.out.println(Arrays.toString(dp[i][y]));
//            }
//            System.out.println();
//        }

//        for(int y = 1; y <= N; y++) {
//            System.out.println(Arrays.toString(dp[y]));
//        }

        int ans = Math.max(dp[0][N][N], Math.max(dp[1][N][N], dp[2][N][N]));

        System.out.println(ans == -1 ? 0 : ans);
    }
}
