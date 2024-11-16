package kr.goldenmine.contest.c987_div2.C;

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
            super.flush(); // TODO 여기에 synchronized가 달려있어서 없애야한다
        }

        @Override
        public void close() throws IOException {
            flush();
            super.close();
        }
    }

    static void fillPattern(int[] arr, int start, int finish, int index) {
        int diff = finish - start;

        if(diff == 2) {
            arr[start] = start;
            arr[start + 1] = start;
        }
        if(diff == 4) {
            arr[start] = start;
            arr[start + 1] = start;
            arr[start + 2] = start + 1;
            arr[start + 3] = start + 1;
        }
        if(diff == 6) {
            arr[start] = start;
            arr[start + 1] = start + 1;
            arr[start + 2] = start + 2;
            arr[start + 3] = start + 2;
            arr[start + 4] = start;
            arr[start + 5] = start + 1;
        }
        if(diff == 8) {
            arr[start] = start;
            arr[start + 1] = start + 1;
            arr[start + 2] = start + 1;
            arr[start + 3] = start + 2;
            arr[start + 4] = start;
            arr[start + 5] = start + 3;
            arr[start + 6] = start + 3;
            arr[start + 7] = start + 2;
        }
        if(diff == 27) {
            arr[start] = start;
            arr[start + 9] = start;
            arr[start + 22] = start + 1;
            arr[start + 23] = start + 2;
            arr[start + 24] = start + 2;
            arr[start + 25] = start;
            arr[start + 26] = start + 1;
            int v = start + 3;
            int count = 0;
            for(int i = start + 1; i < start + 9; i++) {
                arr[i] = v;
                count++;
                if(count % 2 == 0) v++;
            }
            for(int i = start + 10; i < start + 22; i++) {
                arr[i] = v;
                count++;
                if(count % 2 == 0) v++;
            }
        }
    }

//    static boolean solve(int[] arr, int start, int finish) {
//        int diff = finish - start;
//        if(diff % 2 == 0) {
//            int half = diff / 2;
////        System.out.println("(" + start + ", " + finish + ")" + ", " + diff);
//
//            if (diff <= 8) {
//                fillPattern(arr, start, finish, 0);
//                return true;
//            }
//            if (half % 2 == 0) {
//                solve(arr, start, start + half);
//                solve(arr, start + half, finish);
//            } else {
//                solve(arr, start, start + half - 1);
//                solve(arr, start + half - 1, finish);
//            }
//        } else { // 삼조인 경우 찾기
//
//        }
//    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();
        Writer w = new Writer();

        int T = scan.nextInt();
        while(T-- > 0) {
            int N = scan.nextInt();

            if(N % 2 != 0 && N < 27) {
                w.writeInt(-1);
                w.newLine();
                continue;
            }

            if(N % 2 == 0) {
//            int[] arr = new int[N + 1];
//            solve(arr, 1, N + 1);
                for (int i = 1; i <= N; i++) {
                    w.writeInt((i - 1) / 2 + 1);
                    w.write(' ');
                }
                w.newLine();
            } else {
                int[] arr = new int[28];
                fillPattern(arr, 1, 28, 0);
                for(int i = 1; i <= 27; i++) {
                    w.writeInt(arr[i]);
                    w.write(' ');
                }
                int start = 20;
                int count = 0;
                for(int i = 28; i <= N; i++) {
                    w.writeInt(start);
                    w.write(' ');

                    count++;
                    if(count % 2 == 0) start++;
                }
                w.newLine();
            }
        }
        w.close();
    }
}
