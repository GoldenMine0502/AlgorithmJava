package kr.goldenmine.baekjoon.silver.silver2.p17123S;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    static class Reader {
        final int SIZE = 1 << 15;
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
    /*
반례
1
1 3
1000000000
     */

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer(1 << 12);

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();

            int M = scan.nextInt();

            // int로 커버 가능 (
            int[] rs = new int[N + 1];
            int[] cs = new int[N + 1];

            for(int r = 1; r <= N; r++) {
                for(int c = 1; c <= N; c++) {
                    int read = scan.nextInt();

                    // 행에 대해서만 누적합
                    rs[c] += read;

                    // 열에 대해서만 누적합
                    cs[r] += read;
                }
            }

//            System.out.println(Arrays.toString(rs));
//            System.out.println(Arrays.toString(cs));

//            for(int r = 1; r <= N; r++) {
//                for(int c = 1; c <= N; c++) {
//                    System.out.print(rs[r][c]);
//                    System.out.print(" ");
//                }
//                System.out.println();
//            }


            for(int i = 0; i < M; i++) {
                int r1 = scan.nextInt();
                int c1 = scan.nextInt();
                int r2 = scan.nextInt();
                int c2 = scan.nextInt();
                int value = scan.nextInt();

                int cSum = (c2 - c1 + 1) * value;
                for(int r = r1; r <= r2; r++) {
                    cs[r] += cSum;
                }

                int rSum = (r2 - r1 + 1) * value;
                for(int c = c1; c <= c2; c++) {
                    rs[c] += rSum;
                }
//                System.out.println(cSum + ", " + rSum);
//                System.out.println(Arrays.toString(rs));
//                System.out.println(Arrays.toString(cs));
            }

//            System.out.println();
//            System.out.println(Arrays.toString(rs));
//            System.out.println(Arrays.toString(cs));

            w.writeInt(cs[1]);
            for(int c = 2; c <= N; c++) {
                w.write(' ');
                w.writeInt(cs[c]);
            }
            w.write('\n');
            w.writeInt(rs[1]);
            for(int r = 2; r <= N; r++) {
                w.write(' ');
                w.writeInt(rs[r]);
            }
            w.write('\n');
        }

        w.close();
    }
}
