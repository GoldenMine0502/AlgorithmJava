package kr.goldenmine.baekjoon.bronze.bronze3.p2903;

public class Main {
    static class Reader {
        final int SIZE = 1 << 6;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) { if (size < 0) return -1; }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
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

        // 1 -> 9 = 3^2
        // 2 -> 25 = 5^2    + 2
        // 3 -> 81 = 9^2    + 4
        // 4 -> 289 = 17^2  + 8
        // 5 -> 1089 = 33^2 + 16

        int d = 3;
        int p = 2;
        for(int i = 2; i <= N; i++) {
            d += p;
            p <<= 1;
        }
//        System.out.println(d);
        System.out.println(d * d);
    }
}
