package kr.goldenmine.baekjoon.gold.gold2.p32378;

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
        int K = scan.nextInt();
        int S = scan.nextInt();

        long MEGA = 100_000_000_000L;
        if (K > 36) {
            System.out.println("MEGA");
            return;
        }

        int[] sizes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sizes[i] = scan.nextInt();
        }

        long[][] dp = new long[N + 1][K + 1];

        dp[0][0] = S; // 초기 크기
        for (int n = 1; n <= N; n++) {
//            long smallMax = -1;

            for (int k = 0; k <= K; k++) {
                if (k == 0) {
                    if (dp[n - 1][0] > 0)
                        dp[n][0] = dp[n - 1][0] + sizes[n];
                } else {
                    // 강화를 함
                    if (dp[n - 1][k - 1] > 0)
                        dp[n][k] = Math.max(dp[n][k], dp[n - 1][k - 1] * 2);

                    // 강화를 안함
                    if (dp[n][k - 1] > 0)
                        dp[n][k] = Math.max(dp[n][k], dp[n][k - 1] + sizes[n]);
                }

                if(dp[n][k] >= MEGA * 2) {
                    dp[n][k] = MEGA * 2; // 메가 * 2 정도면 계속 -되더라도 ㄱㅊ
                }

                if (dp[n][k] >= MEGA) {
                    System.out.println("MEGA");
                    return;
                }

//                smallMax = Math.max(smallMax, dp[n][k]);
            }

//            if (smallMax <= 0) {
//                System.out.println(-1);
//                return;
//            }
        }
        long max = dp[N][K];
//        for (int k = 0; k <= K; k++) {
//            max = Math.max(max, dp[N][k]);
//        }
        if(max <= 0) {
            System.out.println("-1");
        } else if(max < MEGA) {
            System.out.println(max);
        } else {
            System.out.println("MEGA");
        }
    }
}
