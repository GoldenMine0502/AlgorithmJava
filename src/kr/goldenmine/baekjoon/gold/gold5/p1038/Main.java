package kr.goldenmine.baekjoon.gold.gold5.p1038;

import java.util.LinkedList;
import java.util.Queue;

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

    static int count = -1;
    static int N;

    static boolean dfs(long n, int digit, int digits) {
        boolean res = false;

        for(int i = 0; i <= 9; i++) {
            count++;
            if(count == N) {
                System.out.println(n);
                return true;
            }
        }

        for(int i = 0; i <= 9; i++) {

        }


        return res;
    }

    static int[] chars = new int[20];

    public static boolean check(int n) {
        int i;
        for(i = 20 - 1;; i--) {
            int one = n % 10;
            chars[i] = one;
            n /= 10;
            if(n == 0) break;
        }

        for(; i < 20 - 1; i++) {
            if(chars[i] <= chars[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

//        if(N == 0) {
//            System.out.println(0);
//            return;
//        }

        Queue<Long> queue = new LinkedList<>();
        for(long i = 0; i <= 9; i++) {
            queue.add(i);
        }

        int c = 0;

        while(!queue.isEmpty()) {
            long num = queue.poll();

            if(c == N) {
                System.out.println(num);
                return;
            }

            c++;

            for(int i = 0; i <= 9; i++) {
                if(i == 0 && num == 0) continue;
                if(num % 10 <= i) continue;

                queue.add(num * 10 + i);
            }
        }

        System.out.println(-1);
    }
}
