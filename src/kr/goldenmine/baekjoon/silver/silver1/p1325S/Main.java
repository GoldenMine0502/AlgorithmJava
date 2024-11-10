package kr.goldenmine.baekjoon.silver.silver1.p1325S;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Reader {
        final int SIZE = 1 << 15;
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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[22];

        Writer() {
            this(1 << 10);
        }

        Writer(int size) {
            super(new OutputStreamWriter(System.out), size);
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 10 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 10; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeString(String s) throws IOException {
            for(int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for(int i = off; i < off + len; i++) {
                write(cbuf[i]);
            }
        }

        public void write(int c) throws IOException {
            if (index >= nChars) flush();
            buf[index++] = (char) c;
        }

        @Override
        public void flush() throws IOException {
            super.write(buf, 0, index);
            index = 0;
            super.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    static Queue<Integer> queue = new ArrayDeque<>();

    static void bfs(int start, int[] counts, boolean[] visited, List<List<Integer>> nodes) {
        queue.clear();

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> nexts = nodes.get(current);

            for(int i = 0; i < nexts.size(); i++) {
                int next = nexts.get(i);
                if(!visited[next]) {
                    counts[next]++;
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int N = scan.nextInt();
        int M = scan.nextInt();

        List<List<Integer>> nodes = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            nodes.get(a).add(b);
        }

        int[] counts = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for(int start = 1; start <= N; start++) {
            for(int i = 0; i <= N; i++) {
                visited[i] = false;
            }
            bfs(start, counts, visited, nodes);
        }

        int max = -1;
        for(int i = 1; i <= N; i++) {
            if(counts[i] > max) {
                max = counts[i];
            }
        }

        for(int i = 1; i <= N; i++) {
            if(max == counts[i]) {
                w.writeInt(i);
                w.write(' ');
            }
        }
        w.close();
    }
}
