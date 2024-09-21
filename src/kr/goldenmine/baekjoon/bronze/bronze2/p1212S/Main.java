package kr.goldenmine.baekjoon.bronze.bronze2.p1212S;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    static class Reader {
        final int SIZE = 1 << 10;
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

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        String text = scan.nextString();

        String[] map = {
                "000", "001", "010", "011", "100", "101", "110", "111"
        };

        String[] mapzero = {
                "", "1", "10", "11", "100", "101", "110", "111"
        };

        Writer w = new Writer();

        if(text.charAt(0) == '0') {
            w.write('0');
            w.close();
            return;
        }
        w.writeString(mapzero[text.charAt(0) - '0']);
        for(int i = 1; i < text.length(); i++) {
            w.writeString(map[text.charAt(i) - '0']);
        }
        w.close();
    }
}
