package kr.goldenmine.baekjoon.silver.silver1.p1679;

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

    static int N;

    static class Point {
        int[] counts;
        int value;

        Point() {
            counts = new int[N];
            value = 0;
        }

        Point(int[] counts, int value) {
            this.counts = new int[N];
            System.arraycopy(counts, 0, this.counts, 0, N);
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        N = scan.nextInt();

        int[] nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = scan.nextInt();
        }

        int K = scan.nextInt();

        boolean[] visited = new boolean[1001];
//        Point[] visited = new Point[1001];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point());

        while(!q.isEmpty()) {
            Point u = q.poll();

            for(int i = 0; i < N; i++) {
                if(u.counts[i] < K) { // K보다 작아야 이동할 수 있다
                    int nextIndex = u.value + nums[i];
                    if(nextIndex >= 1000) { continue; }

                    Point next = new Point(nums, nextIndex);
                    next.counts[i] = u.counts[i] + 1;
                    q.add(next);
                    visited[nextIndex] = true;
                }
            }
        }

        for(int i = 1; i <= 100; i++) {
            System.out.println(visited[i]);
        }
/*




 */
    }
}
