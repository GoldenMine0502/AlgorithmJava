package kr.goldenmine.baekjoon.gold.gold4.p2073;

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

    static class Pipe {
        int distance;
        int capacity;

        Pipe(int distance, int capacity) {
            this.distance = distance;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int D = scan.nextInt();
        int P = scan.nextInt();

        Pipe[] pipes = new Pipe[P + 1];
        for (int i = 1; i <= P; i++) {
            pipes[i] = new Pipe(scan.nextInt(), scan.nextInt());
        }

        int[][] dp = new int[D + 1][P + 1]; // 거리가 D일 때 P번째까지 사용하거나 사용하지 않고 가질 수 있는 최대 용량

        // dp[D][P] = max(dp[D - p[i]][P - 1], dp[D - 1][P])

        for (int d = 1; d <= D; d++) {
            for (int p = 1; p <= P; p++) {
                Pipe pipe = pipes[p];
                if (d >= pipe.distance) {
                    if (p == 1) {
                        dp[d][p] = Math.max(pipe.capacity, dp[d - 1][p]);
                    } else {
                        dp[d][p] = Math.max(Math.min(pipe.capacity, dp[d - pipe.distance][p - 1]), dp[d - 1][p]);
                    }
                } else {
                    dp[d][p] = dp[d - 1][p];
                }
            }
        }
        int max = 0;
        for(int i = 1; i <= P; i++) {
            max = Math.max(max, dp[D][i]);
        }
        System.out.println(max);

//        for (int d = 1; d <= D; d++) {
//            System.out.println(Arrays.toString(dp[D]));
//        }
    }
}
