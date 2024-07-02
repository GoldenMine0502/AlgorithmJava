package kr.goldenmine.baekjoon.silver.silver1.p21318S;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

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

    static class UnsynchronizedWriter extends BufferedWriter {
        char[] buf;
        int nChars;
        int index;
        Writer out;

        char[] bufForWriteNumber = new char[22];

        UnsynchronizedWriter(Writer out, int size) {
            super(out);
            this.out = out;
            buf = new char[size];
            nChars = size;
        }

        public void writeInt(int n) throws IOException {
            int d;
            for(d = 10 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if(n == 0) break;
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
            out.write(buf, 0, index);
            index = 0;
            out.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int N = scan.nextInt();

        // 1부터 N까지 실수한 횟수
        int[] arr = new int[N + 1];
        int[] sums = new int[N + 1];
//        for(int i = 1; i <= N; i++) {
//            arr[i] = ;
//        }

//        for(int i = 1; i <= N; i++) {
//            arr[i] = scan.nextInt();
//        }
//        for(int i = 1; i <= N; i++) {
//            sums[i] = sums[i - 1] + ((i < N && arr[i] > arr[i + 1]) ? 1 : 0);
//        }
        sums[0] = 0;
        int last = scan.nextInt();
        for(int i = 2; i <= N; i++) {
            int current = scan.nextInt();
            sums[i - 1] = sums[i - 2] + ((i < N && last > current) ? 1 : 0);
            last = current;
        }

        // [0, 0, 1, 2, 2, 3, 3]
        System.out.println(Arrays.toString(sums));

        int K = scan.nextInt();
        UnsynchronizedWriter w = new UnsynchronizedWriter(new OutputStreamWriter(System.out), 1 << 12);

        for(int i = 0; i < K; i++) {
            int left = scan.nextInt();
            int right = scan.nextInt();

            w.writeInt(sums[right - 1] - sums[left - 1]);
            w.write('\n');
            w.flush();
        }
    }
}
