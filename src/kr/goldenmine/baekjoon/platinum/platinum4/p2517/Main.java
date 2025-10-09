package kr.goldenmine.baekjoon.platinum.platinum4.p2517;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    static class LongSumSegmentTree {
        private long[] arr;
        private long[] tree;

        public LongSumSegmentTree(long[] arr) {
            this.arr = arr;
            this.tree = new long[4 * arr.length];
            init(0, arr.length - 1, 1);
        }

        public long init(int start, int end, int node) {
            if(start == end) return this.tree[node] = arr[start];

            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        public long sum(int left, int right) {
            return sum(0, arr.length - 1, 1, left, right);
        }

        public long sum(int start, int end, int node, int left, int right) {
            // 범위 밖에 있는 경우
            if(left > end || right < start) return 0;

            // 범위 안에 있는 경우
            if(left <= start && end <= right) return tree[node];

            // 범위가 걸쳐있는 경우

            int mid = (start + end) / 2;

            return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
        }

        public void set(int index, long value) {
            set(0, arr.length - 1, 1, index, value);
        }

        public void set(int start, int end, int node, int index, long value) {
            long diff = value - arr[index];

            add(start, end, node, index, diff);
        }

        public void add(int index, long diff) {
            add(0, arr.length - 1, 1, index, diff);
        }

        public void add(int start, int end, int node, int index, long diff) {
            if(index < start || index > end) return;

            tree[node] += diff;

            if (start == end) {
                if(node == 1) arr[index] += diff;
                return;
            }

            int mid = (start + end) / 2;

            add(start, mid, node * 2, index, diff);
            add(mid + 1, end, node * 2 + 1, index, diff);

            if(node == 1) arr[index] += diff;
        }

        public void printTreeAll() {
            for(int i = 0; i < tree.length; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    static class Point {
        int index;
        int value;

        Point(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        int N = scan.nextInt();
        int[] arr = new int[N];
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            points.add(new Point(i, arr[i]));
        }
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.value - o2.value;
            }
        });

        long[] times = new long[N];
        LongSumSegmentTree tree = new LongSumSegmentTree(times);
/*
2
8
10
7
1
9
4
15
 */
        Writer w = new Writer();
        long[] res = new long[N];
        for(int i = 0; i < N; i++) {
            Point p = points.get(i);

            long rank = p.index - tree.sum(0, p.index) + 1;

//            System.out.println(p.index + ", " + p.value + ", " + tree.sum(0, p.index));
            tree.add(p.index, 1);
//            tree.printTreeAll();
            res[p.index] = rank;
        }
        for(int i = 0; i < N; i++) {
            w.writeLong(res[i]);
            w.write('\n');
        }
        w.close();
    }
}
