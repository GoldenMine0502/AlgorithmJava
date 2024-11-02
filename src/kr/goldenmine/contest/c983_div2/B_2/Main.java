package kr.goldenmine.contest.c983_div2.B_2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    /*
    15 8
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    15 9
    1, 2, 3, 4, 5, 6, 7 | 8, 9, 10 | 11, 12, 13, 14, 15
    15 10
    1, 2, 3, 4, 5, 6, 7, 8, 9 | 10 | 11, 12, 13, 14, 15
    15 11
    1, 2, 3, 4, 5, 6, 7, 8, 9 | 10, 11, 12 | 13, 14, 15
    15 12
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 | 12 | 13, 14, 15
    15 13
    1, 2, 3 | 4, 5, 6, 7, 8, 9, 10, 11, 12 | 13 | 14 | 15
    15 14
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 | 14 | 15
    7 4
    1, 2, 3 | 4 | 5, 6, 7
    7 5
    1 | 2, 3, 4 | 5 | 6 | 7
     */

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
            super.flush();
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }


    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int T = scan.nextInt();
        while(T-- > 0) {
            int N = scan.nextInt();
            int K = scan.nextInt();

            if(N == 1) {
                w.writeString("1\n1\n");
                continue;
            }

            int m1 = 1;
            int m2 = K;
            int m3 = K + 1;

            int firstSequenceLen = m2 - m1;
            int lastSequenceLen = N - (K + 1) + 1;

            if(firstSequenceLen == 0 || lastSequenceLen == 0) {
                w.writeString("-1\n");
            } else if(lastSequenceLen % 2 != 0) {
                w.writeInt(3);
                w.write('\n');
                w.writeInt(m1);
                w.write(' ');
                w.writeInt(m2);
                w.write(' ');
                w.writeInt(m3);
                w.write('\n');
            } else {
                if(N >= 5) {
                    int m1_e = 1;
                    int m2_e = 2;
                    int m3_e = K;
                    int m4_e = K + 1;
                    int m5_e = K + 2;
                    if(m5_e > N) {
                        w.writeString("-1\n");
                    } else {
                        w.writeInt(5);
                        w.write('\n');
                        w.writeInt(m1_e);
                        w.write(' ');
                        w.writeInt(m2_e);
                        w.write(' ');
                        w.writeInt(m3_e);
                        w.write(' ');
                        w.writeInt(m4_e);
                        w.write(' ');
                        w.writeInt(m5_e);
                        w.write('\n');
                    }
                } else {
                    w.writeString("-1\n");
                }
            }

        }
        w.close();
    }
}