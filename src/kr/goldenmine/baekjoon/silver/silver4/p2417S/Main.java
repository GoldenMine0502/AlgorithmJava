package kr.goldenmine.baekjoon.silver.silver4.p2417S;

public class Main {
    public static void main(String[] args) throws Exception {
        byte[] buffer = new byte[20];
        System.in.read(buffer);

        long n = 0L;
        int read = 1;
        byte c = buffer[0];
        do n = (n << 3) + (n << 1) + (c & 15);
        while ((c = buffer[read++]) != '\n');

        long l = 0L;
        long r = 3037000500L;

        while(l < r) {
            long mid = (l + r) / 2L;

            if(n <= mid * mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.print(l);
    }
}
