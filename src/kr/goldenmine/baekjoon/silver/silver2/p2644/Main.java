package kr.goldenmine.baekjoon.silver.silver2.p2644;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    static class Point {
        int x;
        int depth;

        Point(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", depth=" + depth +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        int A = scan.nextInt();
        int B = scan.nextInt();
        List<List<Integer>> nodes = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        int M = scan.nextInt();
        for(int i = 0; i < M; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(A, 0));

        boolean[] visited = new boolean[N + 1];
        visited[A] = true;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

//            System.out.println(p);

            if(p.x == B) {
                System.out.println(p.depth);
                return;
            }

            List<Integer> nexts = nodes.get(p.x);

            for(int next : nexts) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Point(next, p.depth + 1));
                }
            }
        }

        System.out.println(-1);
    }
}
