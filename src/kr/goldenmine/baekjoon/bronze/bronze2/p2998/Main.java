package kr.goldenmine.baekjoon.bronze.bronze2.p2998;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
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
            super(new OutputStreamWriter(System.out));
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
        byte[] b = new byte[101];
        int size = System.in.read(b) - 1;

        int[] buffer = new int[40];
        int d = 39;
        for(int i = size - 1; i >= 0; i -= 3) {
            byte b0 = b[i];
            byte b1 = i >= 1 ? b[i - 1] : 48;
            byte b2 = i >= 2 ? b[i - 2] : 48;

            buffer[d--] = (b2 - 48) * 4 + (b1 - 48) * 2 + (b0 - 48);
        }
        ++d;

        Writer w = new Writer(1 << 8);
        for(; d <= 39; d++) {
            w.write(buffer[d] + '0');
        }
        w.close();
    }
}
