package kr.goldenmine.baekjoon.gold.gold2.p2696;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

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

    static Writer w = new Writer();

    static void one(int N, int[] arr) throws IOException {
        PriorityQueue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> high = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        sb.append((N + 1) / 2);
        sb.append('\n');
        int mid = arr[0];
        sb.append(mid);
        sb.append(' ');
        int printed = 1;
        for(int i = 1; i < arr.length; i++) {
            int v = arr[i];
            if(mid < v) { // 크면 high로
                high.offer(v);
            } else { // 작으면 low로
                low.offer(v);
            }

            // low 1개, high 0개 -> 중앙값 변경
            // low 0개, high 2개 -> 중앙값 변경
            // low 1개, high 1개 -> 그대로
            // low 1개, high 2개 -> 그대로 1, [2], 3, 4
            // low 1개, high 3개 -> 중앙값 변경
            if(low.size() > high.size()) {
                high.offer(mid);
                mid = low.poll();
            } else if(low.size() + 1 < high.size()) {
                low.offer(mid);
                mid = high.poll();
            }

            if(i % 2 == 0) {
                sb.append(mid);
                sb.append(' ');
                printed++;
                if(printed % 10 == 0) {
                    sb.append('\n');
                }
            }
//            System.out.println(low + " | " + mid + " | " + high);
        }
        if(printed % 10 != 0)
            sb.append('\n');

        w.writeString(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        Reader scan = new Reader();

        int T = scan.nextInt();

        while(T-- > 0) {
            int N = scan.nextInt();
            int[] arr = new int[N];
            for(int i = 0; i < N; i++) {
                arr[i] = scan.nextInt();
            }

            one(N, arr);
        }
        w.close();
    }
}
