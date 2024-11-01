package kr.goldenmine.baekjoon.silver.silver1.p21317;

import java.util.Arrays;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) { if (size < 0) return "endLine"; }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char)c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32);
            if (c == 45) { c = read(); isMinus = true; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) { if (size < 0) return -12345; }
            if (c == 45) { c = read(); isMinus = true; }
            else if (c == 46) { c = read(); }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c){
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

        int[] smalls = new int[N];
        int[] bigs = new int[N];

        for(int i = 1; i < N; i++) {
            smalls[i] = scan.nextInt();
            bigs[i] = scan.nextInt();
        }

        int K = scan.nextInt();

        int[][] dp = new int[2][N + 1];
        dp[0][0] = 0;
        dp[1][0] = 0;
        dp[0][1] = 0;
        dp[1][1] = 0;

        for(int i = 2; i <= N; i++) {
            dp[0][i] = 9999_9999;
            dp[1][i] = 9999_9999;
        }

        for(int i = 1; i <= N; i++) {
            // 작은 점프
            if(i >= 2) {
                // 매우 큰 점프를 쓰지 않은 경우
                dp[0][i] = Math.min(dp[0][i], dp[0][i - 1] + smalls[i - 1]);

                // 매우 큰 점프를 쓴 경우
                dp[1][i] = Math.min(dp[1][i], dp[1][i - 1] + smalls[i - 1]);
            }

            // 큰 점프
            if(i >= 3) {
                // 매우 큰 점프를 쓰지 않은 경우
                dp[0][i] = Math.min(dp[0][i], dp[0][i - 2] + bigs[i - 2]);

                // 매우 큰 점프를 쓴 경우
                dp[1][i] = Math.min(dp[1][i], dp[1][i - 2] + bigs[i - 2]);
            }

            // 줜나 큰 점프
            if(i >= 4) {
                dp[1][i] = Math.min(dp[1][i], dp[0][i - 3] + K);
            }
        }
//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));
        System.out.println(Math.min(dp[0][N], dp[1][N]));
    }
}
