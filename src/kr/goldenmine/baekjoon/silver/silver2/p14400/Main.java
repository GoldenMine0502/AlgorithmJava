package kr.goldenmine.baekjoon.silver.silver2.p14400;

import java.util.Arrays;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

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

        int[] xs = new int[N];
        int[] ys = new int[N];

        for(int i = 0; i < N; i++) {
            xs[i] = scan.nextInt();
            ys[i] = scan.nextInt();
        }

        Arrays.sort(xs);
        Arrays.sort(ys);

        int midX = xs[N / 2];
        int midY = ys[N / 2];

        long dist = 0;
        for(int i = 0; i < N; i++) {
            dist += Math.abs(midX - xs[i]);
            dist += Math.abs(midY - ys[i]);
        }

        System.out.println(dist);
    }
}