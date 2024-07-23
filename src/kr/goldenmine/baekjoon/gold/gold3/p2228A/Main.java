package kr.goldenmine.baekjoon.gold.gold3.p2228A;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

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

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
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
        int M = scan.nextInt();

//        int[] numbers = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            numbers[i] = scan.nextInt();
//        }

        int[] sum = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + scan.nextInt();
        }

        int MIN = -40000000;
        int[][] dp = new int[N + 1][M + 1];

        for(int n = 0; n <= N; n++) {
            for(int m = 1; m <= M; m++) {
                dp[n][m] = MIN;
            }
        }

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                dp[n][m] = dp[n - 1][m];

                if(m == 1) {
                    dp[n][m] = Math.max(dp[n][m], sum[n]);
                }

                // 이전 값을 쓰려면 최소 한 칸은 띄어야함
                for(int k = n - 2; k >= 0; k--) { // 나눈 구간
                    dp[n][m] = Math.max(dp[n][m], dp[k][m - 1] + sum[n] - sum[k + 1]);
                }

            }
        }
        System.out.println(dp[N][M]);
    }
}
