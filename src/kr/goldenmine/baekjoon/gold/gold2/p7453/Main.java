package kr.goldenmine.baekjoon.gold.gold2.p7453;

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

    // [백준 7453번: 합이 0인 네 정수 (JAVA)](https://bleron.tistory.com/164)
    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int[][] arr = new int[4][N];
        for (int i = 0; i < N; i++) {
            arr[0][i] = scan.nextInt();
            arr[1][i] = scan.nextInt();
            arr[2][i] = scan.nextInt();
            arr[3][i] = scan.nextInt();
        }

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];

        for(int i = 0; i < N * N; i++) {
            AB[i] = arr[0][i / N] + arr[1][i % N];
            CD[i] = arr[2][i / N] + arr[3][i % N];
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long count = 0;
        int left = 0;
        int right = N * N - 1;
        while(left < N*N && right >= 0) {
            long sum = AB[left] + CD[right];

            if(sum < 0) {
                left++;
            } else if(sum > 0) {
                right--;
            } else {
                long leftCount = 1;
                long rightCount = 1;
                while(left + 1 < N * N && AB[left] == AB[left + 1]) {
                    leftCount++;
                    left++;
                }
                while(right > 0 && CD[right] == CD[right - 1]) {
                    rightCount++;
                    right--;
                }

                count += leftCount * rightCount;
                left++; // right--해도 ㄱㅊ
            }
        }
        System.out.println(count);
    }
}
