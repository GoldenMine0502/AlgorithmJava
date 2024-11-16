package kr.goldenmine.baekjoon.silver.silver3.p1449;

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
        int L = scan.nextInt();

//        if(L == 1) {
//            System.out.println(N);
//            return;
//        }

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);

        int count = 0;
        for(int i = 0; i < N; i++) {
            int l = 0;
            for(int j = i; j < i + L - 1 && j < N - 1; j++) {
                if(arr[j] + L > arr[j + 1]) { // 커버 범위인지 체크
                    l++;
                }
            }
            i += l;
            count++;
            System.out.println(i + ", " + l);
        }
        System.out.println(count);
    }
}
