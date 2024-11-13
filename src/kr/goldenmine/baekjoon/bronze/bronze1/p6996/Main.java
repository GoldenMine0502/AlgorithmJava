package kr.goldenmine.baekjoon.bronze.bronze1.p6996;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextString(byte[] ch) throws Exception {
            int len = 0;
            byte c;
            while ((c = read()) < 32) { if (size < 0) return -1; }
            do ch[len++] = c;
            while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return len;
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

        public void write(byte[] cbuf, int off, int len) throws IOException {
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
        Writer w = new Writer();

        int T = scan.nextInt();
        byte[] s1 = new byte[100];
        byte[] s2 = new byte[100];
        int len2, len1;
        while(T-- > 0) {
            len1 = scan.nextString(s1);
            len2 = scan.nextString(s2);

            byte[] counts = new byte[26];
            byte[] counts2 = new byte[26];
            for(int i = 0; i < len1; i++) {
                counts[s1[i] - 'a']++;
            }
            for(int i = 0; i < len2; i++) {
                counts2[s2[i] - 'a']++;
            }

            boolean verify = true;
            for(int i = 0; i < 26; i++) {
                if(counts[i] != counts2[i]) {
                    verify = false;
                    break;
                }
            }

            w.write(s1, 0, len1);
            w.write(' ');
            w.write('&');
            w.write(' ');
            w.write(s2, 0, len2);
            if(verify) {
                w.writeString(" are anagrams.");
            } else {
                w.writeString(" are NOT anagrams.");
            }
            w.write('\n');
        }
        w.close();
    }
}
