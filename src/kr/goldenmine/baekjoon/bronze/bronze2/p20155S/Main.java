package kr.goldenmine.baekjoon.bronze.bronze2.p20155S;

public class Main {
    static class Reader {
        final int SIZE = 1 << 11;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (47 < (c = read()) && c < 58);
            return n;
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

        int[] counts = new int[M + 1];
        for(int i = 0; i < N; i++) {
            counts[scan.nextInt()]++;
        }

        int max = 0;
        for(int i = 1; i <= M; i++) {
            max = Math.max(max, counts[i]);
        }

        System.out.println(max);
    }
}
