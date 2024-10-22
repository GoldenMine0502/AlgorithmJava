package kr.goldenmine.baekjoon.gold.gold3.p1022;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        String nextString() throws Exception {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) < 32) {
                if (size < 0) return "endLine";
            }
            do sb.appendCodePoint(c);
            while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
            return sb.toString();
        }

        char nextChar() throws Exception {
            byte c;
            while ((c = read()) < 32) ; // SPACE 분리라면 <=로, SPACE 무시라면 <로
            return (char) c;
        }

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -1;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        long nextLong() throws Exception {
            long n = 0;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) ;
            if (c == 45) {
                c = read();
                isMinus = true;
            }
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return isMinus ? ~n + 1 : n;
        }

        double nextDouble() throws Exception {
            double n = 0, div = 1;
            byte c;
            boolean isMinus = false;
            while ((c = read()) <= 32) {
                if (size < 0) return -12345;
            }
            if (c == 45) {
                c = read();
                isMinus = true;
            } else if (c == 46) {
                c = read();
            }
            do n = (n * 10) + (c & 15);
            while (isNumber(c = read()));
            if (c == 46) {
                while (isNumber(c = read())) {
                    n += (c - 48) / (div *= 10);
                }
            }
            return isMinus ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        boolean isAlphabet(byte c) {
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

    static int depth(int n) {
        int s = 1;
        for(int i = 1; i <= n; i++) {
            s += 8 * i;
        }

        return s;
    }

    static int getValue(int r, int c) {
        if(r == 0 && c == 0) return 1;

        int depth = Math.max(Math.abs(r), Math.abs(c));
        int shell = depth(depth); // 좌표 (max(r, c), max(r, c))의 값
        int side = 2 * depth;

//        System.out.println(depth + ", " + shell + ", " + side);

        if(r == depth && c == depth) { // 오른쪽 아래 사이드
            return shell;
        } else if(r == depth && c == -depth) { // 왼쪽 아래 사이드
            return shell - 1 * side;
        } else if(r == -depth && c == -depth) {
            return shell - 2 * side;
        } else if(r == -depth && c == depth) {
            return shell - 3 * side;
        }

        if(r == depth && c < depth) {
            return shell - (depth - c);
        } else if(r < depth && c == -depth) {
//            System.out.println("side");
            return shell - 1 * side - (depth - r);
        } else if(r == -depth && c < depth) {
            return shell - 2 * side - (depth + c);
        } else if(r < depth && c == depth) {
            return shell - 3 * side - (depth + r);
        }
        return -999;
    }

    static int digits(int n) {
        int d = 0;
        while(n > 0) {
            d++;
            n /= 10;
        }
        return d;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int R1 = scan.nextInt();
        int C1 = scan.nextInt();
        int R2 = scan.nextInt();
        int C2 = scan.nextInt();

//        System.out.println(getValue(-1, -2));
//        System.out.println(getValue(1, -2));
//        System.out.println(getValue(-2, -1));
//        System.out.println(getValue(-2, 1));

        int[][] res = new int[R2 - R1 + 1][C2 - C1 + 1];

        int maxDigit = 0;
        for(int r = R1; r <= R2; r++) {
            for(int c = C1; c <= C2; c++) {
                int v = getValue(r, c);
                res[r - R1][c - C1] = v;
                maxDigit = Math.max(maxDigit, digits(v));
            }
        }

        Writer w = new Writer();
        for(int r = R1; r <= R2; r++) {
            for (int c = C1; c <= C2; c++) {
                for(int d = digits(res[r - R1][c - C1]); d < maxDigit; d++) {
                    w.write(' ');
                }
                if(c != C1)
                    w.write(' ');
                w.writeInt(res[r - R1][c - C1]);
            }
            w.write('\n');
        }
        w.close();
    }
}
