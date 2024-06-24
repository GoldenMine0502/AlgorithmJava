package kr.goldenmine.baekjoon.gold.gold5.p10427S;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    static final int SIZE = 1 << 13;
    static byte[] buffer = new byte[SIZE];
    static int index, size;

    static int nextInt() throws Exception {
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
        while (47 < (c = read()) && c < 58);
        return isMinus ? ~n + 1 : n;
    }

    static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }

    public static void main(String[] args) throws Exception {
        int T = nextInt();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 10);

        while (T-- > 0) {
            int N = nextInt();

            int[] arr = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                arr[i] = nextInt();
            }

            Arrays.sort(arr);
//            List<Integer> arr = new ArrayList<>(N);
//            for (int i = 0; i < N; i++) {
//                arr.add(nextInt());
//            }
//
//            Collections.sort(arr);

            long[] sums = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                sums[i] = sums[i - 1] + arr[i];
            }

            long sum = 0;
            for (int i = 1; i <= N; i++) {
                long min = Long.MAX_VALUE;
                for (int j = i; j <= N; j++) {
                    // 구간 전체 sliding window
                    min = Math.min(min, (long) arr[j] * i - (sums[j] - sums[j - i]));
                }
                sum += min;
            }

            writer.write(String.valueOf(sum));
            writer.newLine();
        }
        writer.flush();
    }
}
