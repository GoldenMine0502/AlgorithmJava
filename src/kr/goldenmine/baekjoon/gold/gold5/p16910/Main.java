package kr.goldenmine.baekjoon.gold.gold5.p16910;

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
        int W = scan.nextInt();

        int[] works = new int[W];
        for(int i = 0; i < W; i++) {
            works[i] = scan.nextInt();
        }

        final int MAX = 9_9999_9999;
        int[] dp = new int[N + 1];
        dp[0] = 0;

        for(int i = 1; i <= N; i++) {
            dp[i] = MAX;

            // 웍 하나
            for(int w : works) {
                if(i >= w) {
                    dp[i] = Math.min(dp[i], dp[i - w] + 1);
                }
            }

            // 웍 두개
            for(int w1 = 0; w1 < W; w1++) {
                for(int w2 = w1 + 1; w2 < W; w2++) {
                    int sum = works[w1] + works[w2];

                    if(i >= sum) {
                        dp[i] = Math.min(dp[i], dp[i - sum] + 1);
                    }
                }
            }
        }

        if(dp[N] < MAX) {
            System.out.println(dp[N]);
        } else {
            System.out.println(-1);
        }
    }
}

/*
1 2 3
10

6
 */