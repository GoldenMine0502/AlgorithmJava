package kr.goldenmine.contest.c984_div3.D;

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

    static int nextR;
    static int nextC;

    static void nextPosition(int R, int C, int r, int c, int depth) {
        // 왼쪽 위 끝
        if(r == depth && c == depth) {
            nextR = r;
            nextC = c + 1;
        }
        // 오른쪽 위 끝
        else if(r == depth && c == C - depth - 1) {
            nextR = r + 1;
            nextC = c;
        }
        // 오른쪽 아래 끝
        else if(r == R - depth - 1 && c == C - depth - 1) {
            nextR = r;
            nextC = c - 1;
        }
        // 왼쪽 아래 끝
        else if(r == R - depth - 1 && c == depth) {
            nextR = r - 1;
            nextC = c;
        }
        // 왼쪽
        else if(c == depth) {
            nextR = r - 1;
            nextC = c;
        }
        // 위쪽
        else if(r == depth) {
            nextR = r;
            nextC = c + 1;
        }
        // 오른쪽
        else if(c == C - depth - 1) {
            nextR = r + 1;
            nextC = c;
        }
        // 아래
        else if(r == R - depth - 1) {
            nextR = r;
            nextC = c - 1;
        }
    }

    static char[] text = {'1', '5', '4', '3'};

    static boolean traverse(char[][] array, int R, int C, int r, int c, int depth) {
        boolean verify = true;
        for(int i = 0; i < 4; i++) {
            if(array[r][c] != text[i]) {
                verify = false;
                break;
            }
            nextPosition(R, C, r, c, depth);
            r = nextR;
            c = nextC;
        }

        return verify;
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int T = scan.nextInt();
        char[][] array = new char[1001][1001];
        while(T-- > 0) {
            int R = scan.nextInt();
            int C = scan.nextInt();

            for(int r = 0; r < R; r++) {
                String line = scan.nextString();
                for(int c = 0; c < C; c++) {
                    array[r][c] = line.charAt(c);
                }
            }
            int depths = Math.min(R, C) / 2;
            int count = 0;
            for(int depth = 0; depth < depths; depth++) {
                int r = depth;
                int c = depth;
                do {
                    if (traverse(array, R, C, r, c, depth)) {
                        count++;
                    }
                    nextPosition(R, C, r, c, depth);
                    r = nextR;
                    c = nextC;
//                    System.out.println(r + ", " + c);
                } while(!(r == depth && c == depth)); // 돌아올 때 까지 반복
            }
            w.writeInt(count);
            w.write('\n');
        }

        w.close();
    }
}
