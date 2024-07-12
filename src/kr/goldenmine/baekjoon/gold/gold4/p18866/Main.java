package kr.goldenmine.baekjoon.gold.gold4.p18866;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
            if (minus) n = ~n + 1;

            int d;
            for (d = 10 - 1; d >= 0; d--) {
                bufForWriteNumber[d] = (char) (n % 10 + '0');
                n /= 10;
                if (n == 0) break;
            }

            if (minus) {
                bufForWriteNumber[--d] = '-';
            }

            for (; d < 10; d++) {
                write(bufForWriteNumber[d]);
            }
        }

        public void writeString(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                write(s.charAt(i));
            }
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (int i = off; i < off + len; i++) {
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

        int N = scan.nextInt();

        // 순방향 최소가 역방향 최대보다 큰 가장 늦은 지점

        int[] input1 = new int[N + 1];
        int[] input2 = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            input1[i] = scan.nextInt();
            input2[i] = scan.nextInt();
        }

        int[] happy = new int[N + 1];
        int[] happyReverse = new int[N + 2];

        happy[0] = 0;
        happyReverse[N + 1] = Integer.MIN_VALUE;

        int[] fatigue = new int[N + 1];
        int[] fatigueReverse = new int[N + 2];

        fatigue[0] = 0;
        fatigueReverse[N + 1] = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            happy[i] = input1[i] != 0 ? Math.min(happy[i - 1], input1[i]) : happy[i - 1];
            fatigue[i] = input2[i] != 0 ? Math.max(fatigue[i - 1], input2[i]) : fatigue[i - 1];
        }

        // 5 2 3 4 1

        for (int i = N; i >= 1; i--) {
            happyReverse[i] = input1[i] != 0 ? Math.max(happyReverse[i + 1], input1[i]) : happyReverse[i + 1];
            fatigueReverse[i] = input2[i] != 0 ? Math.min(fatigueReverse[i + 1], input2[i]) : fatigueReverse[i + 1];
        }

//        System.out.println(Arrays.toString(happy));
//        System.out.println(Arrays.toString(happyReverse));
//        System.out.println(Arrays.toString(fatigue));
//        System.out.println(Arrays.toString(fatigueReverse));
/*
5
9 1
8 2
0 3
3 0
0 5

2
2 1
1 2

2
2 1
3 2

5
9 1
8 2
0 3
3 0
4 5

5
9 1
8 2
5 3
3 4
2 5
 */

        int answer = 0;
        for (; answer < N - 1;) {
            if (happy[answer + 1] > happyReverse[answer + 2] && fatigue[answer + 1] < fatigueReverse[answer + 2]) {
                answer++;
            } else {
                break;
            }
        }
        if(answer == 0) answer--;
        System.out.println(answer);
    }
}
