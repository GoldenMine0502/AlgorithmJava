package kr.goldenmine.baekjoon.platinum.platinum1.p9548;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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

        static class Pair<T> {
        T one;
        T two;

        public Pair() {
            this.one = null;
            this.two = null;
        }

        public Pair(T one, T two) {
            this.one = one;
            this.two = two;
        }
    }

    static class Rope implements CharSequence {
        String data;
        Rope left;
        Rope right;
        int leftLen;

        public Rope(String data) {
            this.data = data;
            this.leftLen = data.length();
        }

        public Rope(Rope left, Rope right) {
            this.left = left;
            this.right = right;
            this.leftLen = length(left);
        }

        public int length() {
            return length(this);
        }

        public int length(Rope r) {
            int len = 0;
            for (; r != null; r = r.right) {
                len += r.leftLen;
            }
            return len;
        }

        public Rope concat(Rope r) {
            return concat(this, r);
        }

        public Rope concat(Rope one, Rope two) {
            if (one == null) {
                return two;
            } else if (two == null) {
                return one;
            }
            return new Rope(one, two);
        }

        private char charAt(Rope node, int i) {
            if (node.left == null) {
                assert i >= 0 && i < node.leftLen;
                return node.data.charAt(i);
            }

            if (node.leftLen > i) {
                return charAt(node.left, i);
            } else {
                return charAt(node.right, i - node.leftLen);
            }
        }

        public char charAt(int i) {
            return charAt(this, i);
        }

        public Pair<Rope> split(int index) {
            return split(this, index);
        }

        public Pair<Rope> split(Rope nd, int index) {
            if (nd.left == null) {
                assert index >= 0 && index <= nd.leftLen;
                Pair<Rope> nodes = new Pair<Rope>();
                if (index == 0) {
                    nodes.one = null;
                    nodes.two = nd;
                } else if (index == nd.leftLen) {
                    nodes.one = nd;
                    nodes.two = null;
                } else {
                    nodes.one = new Rope(nd.data.substring(0, index));
                    nodes.two = new Rope(nd.data.substring(index, nd.leftLen));
                }
                return nodes;
            } else if (index == nd.leftLen) {
                return new Pair<Rope>(nd.left, nd.right);
            } else if (index < nd.leftLen) {
                Pair<Rope> pair = split(nd.left, index);
                return new Pair<Rope>(pair.one, concat(pair.two, nd.right));
            } else {
                Pair<Rope> pair = split(nd.right, index - nd.leftLen);
                return new Pair<Rope>(concat(nd.left, pair.one), pair.two);
            }
        }

        public Rope subSequence(int start, int end) {
            Pair<Rope> sp1 = split(start);
            Pair<Rope> sp2 = sp1.two.split(end - start);
            return sp2.one;
        }

        public Rope insert(Rope r, int index) {
            Pair<Rope> pair = this.split(index);
            return concat(concat(pair.one, r), pair.two);
        }

        public String toString() {
            if (left == null) return data;
            return left.toString() + right.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int T = scan.nextInt();
        while(T-- > 0) {
            String input = scan.nextString();
            Rope root = new Rope(input);

            while(true) {
                char first = scan.nextChar();

                // END Readed
                if(first == 'E') {
                    scan.nextChar();
                    scan.nextChar();
                    break;
                }

                if(first == 'I') {
                    scan.nextChar();
                    String X = scan.nextString();
                    int Y = scan.nextInt();

                    if(Y == root.length()) {
                        root = root.concat(new Rope(X));
                    } else {
                        root = root.insert(new Rope(X), Y);
                    }
                } else if(first == 'P') {
                    scan.nextChar();
                    int X = scan.nextInt();
                    int Y = scan.nextInt();

//                    System.out.println(root);
                    w.writeString(root.subSequence(X, Y + 1).toString());
//                    w.write(root.subSequence(X, Y).toString());
//                    w.write(root.toString());
                    w.write('\n');

//                    System.out.println(w.index);
                }
            }
        }

        w.close();
    }
}
