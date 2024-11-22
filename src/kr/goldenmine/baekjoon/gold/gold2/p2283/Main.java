package kr.goldenmine.baekjoon.gold.gold2.p2283;

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

        final int MAX = 1000000;
        int N = scan.nextInt();
        int K = scan.nextInt();
        int[] arr = new int[MAX + 1];

        for(int i = 0; i < N; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            arr[a]++;
            arr[b]--;
        }

        long[] counts = new long[MAX + 1];
        for(int i = 0; i <= MAX; i++) {
            if(i > 0) {
                counts[i] = counts[i - 1] + arr[i];
            } else {
                counts[i] = arr[i];
            }
        }

        long[] prefixSum = new long[MAX + 1];
        for(int i = 0; i <= MAX; i++) {
            if(i > 0) {
                prefixSum[i] = prefixSum[i - 1] + counts[i];
            } else {
                prefixSum[i] = arr[i];
            }
        }

//        System.out.println(Arrays.toString(counts));
//        System.out.println(Arrays.toString(prefixSum));

        int left = 0;
        int right = 0;
        int a = 0;
        int b = -1;
        while(left <= MAX && right <= MAX) {
            long sum = prefixSum[right] - (left > 0 ? prefixSum[left - 1] : 0);

//            System.out.println(left + ", " + right + " " + sum);
            if(sum < K) {
                right++;
            } else if(sum > K) {
                left++;
            } else {
                a = left;
                b = right;
                break;
            }
        }
        System.out.println(a + " " + (b + 1));
    }
}
