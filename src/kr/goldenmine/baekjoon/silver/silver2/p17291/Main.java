package kr.goldenmine.baekjoon.silver.silver2.p17291;

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

        // N 년도에 태어난 벌레
        int[] worms = new int[N + 1];
        worms[1] = 1;

        for(int i = 2; i <= N; i++) {
            int total = 0;
            // 1년 전
            total += worms[i - 1];
            // 2년 전
            total += worms[i - 2];
            // 3년 전
            if(i >= 3) {
                total += worms[i - 3];
                if ((i - 3) % 2 == 1) // 3년전이 홀수 년도이면
                    worms[i - 3] = 0;
            }
            // 3년 전
//            if(i >= 3) {
//                if ((i - 3) % 2 != 1) // 3년전이 짝수 년도이면
//                    total += worms[i - 3];
////                else
////                    total -= dp[i - 3];
//            }
            // 4년 전
            if(i >= 4) {
                if ((i - 4) % 2 == 0) {
                    total += worms[i - 4];
                    worms[i - 4] = 0;
                }
//                else
//                    total -= dp[i - 4];
            }

//            int total = dp[i - 1] * 2;
//
//            if(i >= 3 && (i - 3) % 2 == 1) { // 3년 전이 홀수 년도이면 3년전 벌레 죽여
//                total -= dp[i - 3];
//            }
//            if(i >= 4 && (i - 4) % 2 == 0) { // 4년 전이 짝수 년도이면 4년전 벌레 죽여
//                total -= dp[i - 4];
//            }

            worms[i] = total;
        }
        int sum = 0;
        for(int i = Math.max(N - (N % 2 == 0 ? 3 : 4), 0); i <= N; i++) {
            sum += worms[i];
        }
        System.out.println(sum);
//        System.out.println(Arrays.toString(worms));
//        System.out.println(worms[N]);
    }
}
