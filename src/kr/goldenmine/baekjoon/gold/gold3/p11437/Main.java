package kr.goldenmine.baekjoon.gold.gold3.p11437;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

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

    // 기본적으로 BufferedWriter에는 모든 메소드에 synchronized가 붙어있으므로 최대한 synchronized를 빼주도록 작동
    static class Writer extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;

        char[] bufForWriteNumber = new char[24];

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
            for(d = 12 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 12; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeLong(long n) throws IOException {
            boolean minus = n < 0;
            if(minus) n = ~n + 1;

            int d;
            for(d = 22 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
            }

            if(minus) {
                bufForWriteNumber[--d] = '-';
            }

            for(; d < 22; d++) {
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

        public void newLine() throws IOException {
            write('\n');
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

    static class Point {
        int value;
        int depth;

        Point(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }

    static void removeNotAncestor(ArrayList<ArrayList<Integer>> nodes, boolean[] visited, int N, int[] depths) {
        for(int start = 1; start <= N; start++) {
            List<Integer> filtered = new ArrayList<>();
            ArrayList<Integer> nexts = nodes.get(start);
//            System.out.println(nexts);
            for(int i = 0; i < nexts.size(); i++) {

//                System.out.println(i + ", " + nexts.get(i) + ", " + depths[nexts.get(i)] + ", " + depths[start]);
                if(depths[nexts.get(i)] < depths[start]) {
                    filtered.add(nexts.get(i));
//                    break;
                }
            }
            nexts.clear();
            nexts.addAll(filtered);
//            for(int i = filtered.size() - 1; i >= 0; i--) {
//                nexts.remove(filtered.get(i));
//            }
//            System.out.println(start + ": " + nexts + ", ");
        }
    }

    static void calcDepth(ArrayList<ArrayList<Integer>> nodes, int start, int[] depths) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(start, 0));

        boolean[] visited = new boolean[depths.length];
        visited[start] = true;

        while(!queue.isEmpty()) {
            Point point = queue.poll();

            depths[point.value] = point.depth;

            for(int nextValue : nodes.get(point.value)) {
                Point next = new Point(nextValue, point.depth + 1);

                if(!visited[next.value]) {
                    visited[next.value] = true;
                    queue.add(next);
                }
            }
        }
    }

    static void dfs(ArrayList<ArrayList<Integer>> nodes, boolean[] visited, int start, boolean value) {
        if(visited[start] == value) {
            return;
        }
        visited[start] = value;
        for(int next : nodes.get(start)) {
//            if(depths[next] < depths[start]) {
            dfs(nodes, visited, next, value);
//            }
        }
    }

    static int value = -1;
    static void check(ArrayList<ArrayList<Integer>> nodes, boolean[] visited, int start) {
        if(visited[start]) {
            value = start;
//            System.out.println("value: " + value);
            return;
        }

        for(int next : nodes.get(start)) {
//            if(next == 1) continue;
//            if(depths[next] < depths[start]) {
            if(visited[next]) {
                value = next;
//                    System.out.println("value: " + value);
                return;
            }
            if (value >= 0) return;
            check(nodes, visited, next);
//            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        int[] depths = new int[N + 1];

        for(int i = 0; i < N - 1; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }
        calcDepth(nodes, 1, depths);
        removeNotAncestor(nodes, null, N, depths);
//        System.out.println(Arrays.toString(depths));

        Writer w = new Writer();
        int M = scan.nextInt();
        boolean[] visited = new boolean[N + 1];
        for(int i = 0; i < M; i++) {
//            boolean[] visited = new boolean[N + 1];
            int a = scan.nextInt();
            int b = scan.nextInt();
            dfs(nodes, visited, a, true);
//            System.out.println(Arrays.toString(visited));
            value = -1;
            check(nodes, visited, b);
//            System.out.println(value);
            w.writeInt(value);
            w.newLine();

            dfs(nodes, visited, a, false);
        }
        w.close();
    }
}
