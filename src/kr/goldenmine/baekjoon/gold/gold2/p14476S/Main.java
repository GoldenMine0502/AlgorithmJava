package kr.goldenmine.baekjoon.gold.gold2.p14476S;

public class Main {
    static class Reader {
        final int SIZE = 1 << 15;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
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

    public static int gcd(int a, int b) {
        while (b != 0) {
            int gcd = a % b;
            a = b;
            b = gcd;
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int[] arr = new int[100_0001];
        int[] gcds = new int[100_0002];
        int[] gcds_reverse = new int[100_0002];

        arr[0] = arr[1] = gcds[1] = scan.nextInt();
        for (int i = 2; i <= N; i++) {
            gcds[i] = gcd(gcds[i - 1], arr[i] = scan.nextInt());
        }

        gcds_reverse[N + 1] = gcds[N - 1];

        gcds_reverse[N] = arr[N];
        for (int i = N - 1; i >= 1; i--) {
            gcds_reverse[i] = gcd(gcds_reverse[i + 1], arr[i]);
        }

        gcds[0] = gcds_reverse[2];

        int max = 0;
        int removedIndex = -1;
        for (int i = 1; i <= N; i++) {
            int nextValue = gcd(gcds[i - 1], gcds_reverse[i + 1]);

            if (max < nextValue) {
                removedIndex = i;
                max = nextValue;
            }
        }

        if (arr[removedIndex] % max == 0) { // 나눠 떨어지면 약수
            System.out.println(-1);
        } else { // 나눠 떨어지지 않으면 약수 아님
            System.out.println(String.valueOf(max) + ' ' + arr[removedIndex]);
        }
    }
}
