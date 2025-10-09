package kr.goldenmine.baekjoon.platinum.platinum5.p1517;

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

    static int[] tmp;
    static long result = 0;
    public static int solve(int[] arr, int a, int b) {
        if(a == b) {
            return 0;
        }
        int mid = (a + b) / 2;

        solve(arr, a, mid);
        solve(arr, mid + 1, b);

        // 정렬 시도
        // 0 1 2
        int left = a;
        int right = mid + 1;

        int count = 0;
        int idx = a;
//        System.out.println(left + ", " + mid + ", " + right + ", a: " + a + ", b: " + b);
        while(left <= mid && right <= b) {
            if(arr[left] <= arr[right]) { // right가 더 크다? left 추가해서 개수 안 늘어남
                tmp[idx++] = arr[left++];
            } else { // left가 더 크다? 남은 수의 개수만큼 수가 늘어남
                tmp[idx++] = arr[right++];
                result += right - idx;
            }
//            System.out.println(Arrays.toString(tmp));
        }
        while(left <= mid) {
            tmp[idx++] = arr[left++];
        }
        while(right <= b) {
            tmp[idx++] = arr[right++];
        }
        // 원래 위치로 복사
        for(int i = a; i <= b; i++) {
            arr[i] = tmp[i];
        }
//        result += count;
        return count;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        tmp = new int[N];
        int c = solve(arr, 0, N - 1);
        System.out.print(result);
    }
}
