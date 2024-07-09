package kr.goldenmine.baekjoon.silver.silver2.p15990;

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

        final int MOD = 1_000_000_009;

        int[][] dp = new int[4][100001]; // dp[마지막 값][N] = dp[마지막 값 아님][N - n]
        dp[1][1] = 1; // 1
        dp[2][2] = 1; // 2
        dp[3][3] = 1; // 1 + 2, 2 + 1, 3
        dp[1][3] = 1; // 1 + 2, 2 + 1, 3
        dp[2][3] = 1; // 1 + 2, 2 + 1, 3
        for (int i = 4; i <= 100000; i++) {
            // + 1 하는 경우
            dp[1][i] += dp[2][i - 1];
            dp[1][i] += dp[3][i - 1];
            dp[1][i] %= MOD;

            // + 2 하는 경우
            dp[2][i] += dp[1][i - 2];
            dp[2][i] += dp[3][i - 2];
            dp[2][i] %= MOD;

            // + 3 하는 경우
            dp[3][i] += dp[1][i - 3];
            dp[3][i] += dp[2][i - 3];
            dp[3][i] %= MOD;
        }

        int T = scan.nextInt();

        while (T-- > 0) {
            int N = scan.nextInt();

            System.out.println((((dp[1][N] + dp[2][N]) % MOD) + dp[3][N]) % MOD);
        }
    }
}
