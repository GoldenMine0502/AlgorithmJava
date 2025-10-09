package kr.goldenmine.inu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class ListAndQuery {
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
            super.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    static int find(int[] parent, int value) {
        if(parent[value] == value)
            return value;
        return parent[value] = find(parent, parent[value]); // path compression
    }

    // by rank
    static void union(int[] parent, int[] rank, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y) return;

        if(rank[x] < rank[y]) {
            parent[x] = y;
            rank[y] += rank[x];
        } else {
            parent[y] = x;
            rank[x] += rank[y];
        }
    }

    static boolean isUnion(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        return x == y;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int N = scan.nextInt();

        HashMap<Integer, Integer> input = new HashMap<>();
        int[] mapping = new int[N + 1];

        int[] parent = new int[N + 1];
        int[] rank = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for(int i = 1; i <= N; i++) {
            int n = scan.nextInt();
            if(!input.containsKey(n)) {
                input.put(n, input.size() + 1);
                mapping[input.size()] = n;
            }
            int idx = input.get(n);
            parent[i] = idx;
            rank[idx]++;
        }
        System.out.println(input);
        System.out.println(Arrays.toString(mapping));
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(rank));
        // 1 1 1
        int M = scan.nextInt();
        for(int i = 1; i <= M; i++) {
            int command = scan.nextInt();

            if(command == 1) {
                int x = scan.nextInt();
                int y = scan.nextInt();

                if (input.containsKey(y)) {
                    if (input.containsKey(x)) {
                        union(parent, rank, find(parent, input.get(x)), find(parent, input.get(y)));
                        mapping[input.get(x)] = y;
                        input.put(y, input.get(x));
                        input.remove(x);
                    }
                } else {
                    if(input.containsKey(x)) {
                        input.put(y, input.get(x));
                        mapping[input.get(x)] = y;
                        input.remove(x);
                    }
                }
            } else if(command == 2) {
                int x = scan.nextInt();
                w.writeInt(mapping[find(parent, x)]);
                w.write('\n');
            }
            System.out.println(input);
            System.out.println(Arrays.toString(mapping));
            System.out.println(Arrays.toString(parent));
            System.out.println(Arrays.toString(rank));
        }

        w.close();
    }
}
